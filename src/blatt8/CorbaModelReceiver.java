package blatt8;

import java.io.IOException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import forum.framework.ForumModel;
import blatt8.gen.AlreadyBoundException;
import blatt8.gen.CorbaForumModel;
import blatt8.gen.CorbaForumModelHelper;
import blatt8.gen.CorbaForumModelPOA;
import blatt8.gen.CorbaForumView;
import blatt8.gen.NotBoundException;

public class CorbaModelReceiver extends CorbaForumModelPOA implements Runnable {
	private ForumModel model;
	private String[] args;
	private static CorbaModelReceiver instance = null;
	
	private CorbaModelReceiver(String[] args){
		this.args = args;
		model = ForumModel.INSTANCE;
	}
	
	public static CorbaModelReceiver getInstance(String[] args){
		if(instance == null){
			instance = new CorbaModelReceiver(args);
		}
		return instance;
	}
	
	@Override
	public void registerView(String name, CorbaForumView view) throws AlreadyBoundException {
		try{
			model.registerView(name, new CorbaViewForwarder(view));
		} catch (java.rmi.AlreadyBoundException e) {
			throw new AlreadyBoundException(e.getMessage());
		}
	}

	@Override
	public void deregisterView(String name) throws NotBoundException {
		try{
			model.deregisterView(name);
		} catch (java.rmi.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveNorth(String name) throws NotBoundException {
		try{
			model.moveNorth(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveEast(String name) throws NotBoundException {
		try{
			model.moveEast(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveSouth(String name) throws NotBoundException {
		try{
			model.moveSouth(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveWest(String name) throws NotBoundException {
		try {
			model.moveWest(name);
		} catch (java.rmi.NotBoundException | IOException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(this);
			CorbaForumModel corbaModel = CorbaForumModelHelper.narrow(ref);
			nameService.rebind(nameService.to_name("ForumServer"), corbaModel);
			orb.run();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
