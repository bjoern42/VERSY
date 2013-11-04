package blatt4;
import blatt4.Message;
import blatt4.Process;


public class RAResponse extends Message {
	private final int logicalTime;	
	
	RAResponse(Process sender, int logicalTime) {
		super(sender);
		this.logicalTime = logicalTime;
	}
	
	int getLogicalTime() {
		return this.logicalTime;
	}
	
	@Override
	public void getProcessedBy(Process process) {
		((RAProcess)process).process(this);
	}

}
