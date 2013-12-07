package blatt7;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.IForumView;

public class RmiModelForwarder implements IRemoteForumModel{
	private IRemoteForumModel remote;
	
	public RmiModelForwarder() throws RemoteException, NotBoundException{
		String name = getClass().getPackage()+".rmi_forum_model_registry";
		Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
		remote = (IRemoteForumModel)registry.lookup(name);
	}
	
	@Override
	public void deregisterView(String arg0) throws NotBoundException,IOException {
		remote.deregisterView(arg0);
	}

	@Override
	public void moveEast(String arg0) throws NotBoundException, IOException {
		remote.moveEast(arg0);
	}

	@Override
	public void moveNorth(String arg0) throws NotBoundException, IOException {
		remote.moveNorth(arg0);
	}

	@Override
	public void moveSouth(String arg0) throws NotBoundException, IOException {
		remote.moveSouth(arg0);
	}

	@Override
	public void moveWest(String arg0) throws NotBoundException, IOException {
		remote.moveWest(arg0);
	}

	@Override
	public void registerView(String arg0, IForumView arg1)throws AlreadyBoundException, IOException {
		IRemoteForumView stub = (IRemoteForumView) UnicastRemoteObject.exportObject(new RmiViewReceiver(arg1), 0);
		remote.registerView(arg0, stub);
	}

}
