package blatt6;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import forum.framework.IForumModel;
import forum.framework.IForumView;

public class LocalModelForwarder implements IForumModel{
	private LocalModelReceiver localModelReceiver= null;
	
	public LocalModelForwarder(){
		localModelReceiver = LocalModelReceiver.getInstance();
	}
	
	@Override
	public void deregisterView(String arg0) throws NotBoundException,IOException {
		localModelReceiver.deregisterView(arg0);
	}

	@Override
	public void moveEast(String arg0) throws NotBoundException, IOException {
		localModelReceiver.moveEast(arg0);
	}

	@Override
	public void moveNorth(String arg0) throws NotBoundException, IOException {
		localModelReceiver.moveNorth(arg0);
	}

	@Override
	public void moveSouth(String arg0) throws NotBoundException, IOException {
		localModelReceiver.moveSouth(arg0);
	}

	@Override
	public void moveWest(String arg0) throws NotBoundException, IOException {
		localModelReceiver.moveWest(arg0);
	}

	@Override
	public void registerView(String arg0, IForumView arg1)throws AlreadyBoundException, IOException {
		localModelReceiver.registerView(arg0, arg1);
	}

}
