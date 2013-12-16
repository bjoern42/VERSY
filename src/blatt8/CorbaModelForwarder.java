package blatt8;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import blatt8.gen.CorbaForumModel;
import blatt8.gen.CorbaForumModelHelper;
import blatt8.gen.CorbaForumView;
import blatt8.gen.CorbaForumViewHelper;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class CorbaModelForwarder implements IForumModel {
	private CorbaForumModel server;
	private ORB orb;
	
	public CorbaModelForwarder(String[] args){
		try{
			orb = ORB.init(args, null);
			NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			server = CorbaForumModelHelper.narrow(nameService.resolve_str("ForumServer"));	
		}catch(Exception e){
			System.out.println(e);
		}	 
	}
	
	@Override
	public void registerView(String p0, final IForumView p1)throws AlreadyBoundException, IOException {
		try {
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();			
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(new CorbaViewReceiver(p1));
			CorbaForumView corbaView = CorbaForumViewHelper.narrow(ref);
			server.registerView(p0, corbaView);
		} catch (blatt8.gen.AlreadyBoundException e){
			throw new AlreadyBoundException(e.getMessage());
		} catch (Exception e){
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public void deregisterView(String p0) throws NotBoundException, IOException {
		try {
			server.deregisterView(p0);
		} catch (blatt8.gen.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveNorth(String p0) throws NotBoundException, IOException {
		try {
			server.moveNorth(p0);
		} catch (blatt8.gen.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveEast(String p0) throws NotBoundException, IOException {
		try {
			server.moveEast(p0);
		} catch (blatt8.gen.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveSouth(String p0) throws NotBoundException, IOException {
		try {
			server.moveSouth(p0);
		} catch (blatt8.gen.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

	@Override
	public void moveWest(String p0) throws NotBoundException, IOException {
		try {
			server.moveWest(p0);
		} catch (blatt8.gen.NotBoundException e) {
			throw new NotBoundException(e.getMessage());
		}
	}

}
