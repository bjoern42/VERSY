package blatt7;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewForwarder implements IRemoteForumView{
	private IForumView forumView= null;
	
	public RmiViewForwarder(IForumView view){
		forumView = view;
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		forumView.notifyView(arg0);
	}

}
