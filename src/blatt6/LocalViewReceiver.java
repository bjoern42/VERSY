package blatt6;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class LocalViewReceiver implements IForumView, Runnable{
	private IForumView forumView = null;
	
	public LocalViewReceiver(IForumView view){
		forumView = view;
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		forumView.notifyView(arg0);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
