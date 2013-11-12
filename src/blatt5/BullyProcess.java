package blatt5;

import java.util.HashSet;
import java.util.UUID;

import disy.Process;

public class BullyProcess extends Process{
	private volatile boolean elected = true;
	private HashSet<UUID> uuids = new HashSet<UUID>();
	
	protected BullyProcess(int id) {
		super(id);
	}

	public void process(ElectionTrigger electionTrigger) {
		magic(UUID.randomUUID());
	}

	public void process(ElectionResponse electionResponse) {
		elected = false;
	}

	public void process(ElectionRequest electionRequest) {
		Process sender = electionRequest.getSender();
		sender.receiveMessage(new ElectionResponse(this));
		
		if(!uuids.contains(electionRequest.getUuid())){
			uuids.add(electionRequest.getUuid());
			
			magic(electionRequest.getUuid());
		}
	}
	
	private void magic(UUID uuid){
		for (Process process : destinations.values()) {
			if(getID() < process.getID()){
				process.receiveMessage(new ElectionRequest(this, uuid));
			}
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(elected){
			System.out.println(this+" has been elected");
		}
	}

}
