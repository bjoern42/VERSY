package blatt7;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.ForumModel;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class RmiModelReceiver implements IRemoteForumModel, Runnable{
	private static RmiModelReceiver instance = null;
	private IForumModel forumModel = null;
	
	private RmiModelReceiver(){
		forumModel = ForumModel.INSTANCE;
	}
	
	public static RmiModelReceiver getInstance(){
		if(instance == null){
			instance = new RmiModelReceiver();
		}
		return instance;
	}
	
	@Override
	public void deregisterView(String arg0) throws NotBoundException,IOException {
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
	public void registerView(String arg0, IForumView arg1)throws AlreadyBoundException, IOException {
		forumModel.registerView(arg0, new RmiViewForwarder(arg1));
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Registering server!");
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			IRemoteForumModel stub = (IRemoteForumModel) UnicastRemoteObject.exportObject(this, 0);
			registry.bind(getClass().getPackage()+".rmi_forum_model_registry", stub);
		} catch (RemoteException e) {
			System.out.println(e);
		} catch (AlreadyBoundException e) {
			System.out.println(e);
		}
	}

}
