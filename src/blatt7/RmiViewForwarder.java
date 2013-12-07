package blatt7;

import java.io.IOException;
import java.util.Map;

import forum.framework.Position;

public class RmiViewForwarder implements IRemoteForumView{
	private IRemoteForumView forumView= null;
	
	public RmiViewForwarder(IRemoteForumView view){
		System.out.println("test");
		forumView = view;
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		forumView.notifyView(arg0);
	}

}
