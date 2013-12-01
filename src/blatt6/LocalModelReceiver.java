package blatt6;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import forum.framework.ForumModel;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class LocalModelReceiver implements IForumModel, Runnable{
	private static LocalModelReceiver instance = null;
	private IForumModel forumModel = null;
	
	private LocalModelReceiver(){
		forumModel = ForumModel.INSTANCE;
	}
	
	public static LocalModelReceiver getInstance(){
		if(instance == null){
			instance = new LocalModelReceiver();
		}
		return instance;
	}
	
	@Override
	public void deregisterView(String arg0) throws NotBoundException, IOException {
		forumModel.deregisterView(arg0);
	}

	@Override
	public void moveEast(String arg0) throws NotBoundException, IOException {
		forumModel.moveEast(arg0);
	}

	@Override
	public void moveNorth(String arg0) throws NotBoundException, IOException {
		forumModel.moveNorth(arg0);
	}

	@Override
	public void moveSouth(String arg0) throws NotBoundException, IOException {
		forumModel.moveSouth(arg0);
	}

	@Override
	public void moveWest(String arg0) throws NotBoundException, IOException {
		forumModel.moveWest(arg0);
	}

	@Override
	public void registerView(String arg0, IForumView arg1) throws AlreadyBoundException, IOException {
		forumModel.registerView(arg0, arg1);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
