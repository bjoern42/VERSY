package blatt4;

public class RAProcess extends Process {
	private int logicalTime;
	
	protected RAProcess(int id) {
		super(id);
		logicalTime = 0;
	}

	public void process(RATrigger message) {
		multicast(new RARequest(this, logicalTime));
	}

	public void process(RARequest raRequest) {
		if(raRequest.getLogicalTime() < logicalTime){
			receiveMessage(new RAResponse(raRequest.getSender(), logicalTime));
		}else{
			process(new RAResponse(this, logicalTime));
		}
	}

	public void process(RAResponse raResponse) {
		
		logicalTime++;
		System.out.println("process "+getID()+" holds lock");
	}
}
