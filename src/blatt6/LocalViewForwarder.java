package blatt6;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class LocalViewForwarder implements IForumView{
	private LocalViewReceiver localViewReceiver= null;
	
	public LocalViewForwarder(){
		localViewReceiver = LocalViewReceiver.getInstance();
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		localViewReceiver.notifyView(arg0);
	}

}
