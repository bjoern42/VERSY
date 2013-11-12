package blatt4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import disy.Process;

public class RAProcess extends Process {
	private CountDownLatch latch;
	private int logicalTime;
	private volatile State state = State.RELEASED;

	private Queue<Process> queue = new LinkedList<Process>();

	private enum State {
		HELD, RELEASED, WANTED
	};

	protected RAProcess(int id) {
		super(id);
		logicalTime = 0;
	}

	public void process(RATrigger raTrigger) {
		state = State.WANTED;
		// System.out.println("trigger "+this);
		latch = new CountDownLatch(destinations.size());
		multicast(new RARequest(this, logicalTime));
	}

	public void process(RARequest raRequest) {
		// System.out.println("request to "+this+" from "+raRequest.getSender()+" state "+state);
		RAProcess sender = (RAProcess) raRequest.getSender();

		if (logicalTime < raRequest.getLogicalTime()) {
			logicalTime = raRequest.getLogicalTime() + 1;
		}

		if (state == State.HELD || (state == State.WANTED && logicalTime <= raRequest.getLogicalTime())) {
			queue.add(sender);
		}else{
			sender.receiveMessage(new RAResponse(this, logicalTime));
		}

	}

	public void process(RAResponse raResponse) {
		if (logicalTime < raResponse.getLogicalTime()) {
			logicalTime = raResponse.getLogicalTime() + 1;
		}

		// System.out.println("response to "+this+" from "+raResponse.getSender());
		latch.countDown();
		try {
			latch.await();
			if (state != State.HELD) {
				criticalArea();
				// System.out.println("notifying everyone waiting for "+this);

				Process p;
				while ((p = queue.poll()) != null) {
					p.receiveMessage(new RAResponse(this, logicalTime));
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void criticalArea() throws InterruptedException {
		state = State.HELD;
		System.out.println(this + " holds lock");
		TimeUnit.SECONDS.sleep(3);
		System.out.println(this + " releases lock");
		state = State.RELEASED;
	}
}
