package blatt7;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewReceiver implements IRemoteForumView, Runnable {
	private IForumView forumView = null;

	public RmiViewReceiver(IForumView view){
		forumView = view;
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		forumView.notifyView(arg0);
	}
	
	@Override
	public void run() {
		System.out.println("test");
	}
}
