package blatt4;
import blatt4.Message;
import blatt4.Process;


public class RATrigger extends Message {
	RATrigger() {
		super(null);
	}
	
	@Override public void getProcessedBy(Process process) {
		((RAProcess) process).process(this);
	}

}
