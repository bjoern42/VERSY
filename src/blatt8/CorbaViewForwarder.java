package blatt8;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import blatt8.gen.CorbaForumView;
import blatt8.gen.PositionedAvatar;
import forum.framework.IForumView;
import forum.framework.Position;


public class CorbaViewForwarder implements IForumView{
	private CorbaForumView receiver;
	
	public CorbaViewForwarder(CorbaForumView view){
		this.receiver = view;
	}

	@Override
	public void notifyView(Map<String, Position> p0) throws IOException {
		System.out.println("view forwarder");
		List<PositionedAvatar> folks = new LinkedList<PositionedAvatar>();
		for(String name:p0.keySet()){
			folks.add(new PositionedAvatar(name, new blatt8.gen.Position(p0.get(name).getX(), p0.get(name).getY())));
		}
		
		receiver.notifyView(folks.toArray(new PositionedAvatar[folks.size()]));
	}
	
}
