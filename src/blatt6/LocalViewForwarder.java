package blatt6;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class LocalViewForwarder implements IForumView{
	private IForumView forumView= null;
	
	public LocalViewForwarder(IForumView view){
		forumView = view;
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		forumView.notifyView(arg0);
	}

}
