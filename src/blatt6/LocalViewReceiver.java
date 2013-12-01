package blatt6;

import java.io.IOException;
import java.util.Map;

import forum.framework.ForumView;
import forum.framework.IForumView;
import forum.framework.Position;

public class LocalViewReceiver implements IForumView, Runnable{
	private static LocalViewReceiver instance = null;
	private IForumView forumView = null;
	
	private LocalViewReceiver(){
		forumView = new ForumView();
	}
	
	public static LocalViewReceiver getInstance(){
		if(instance == null){
			instance = new LocalViewReceiver();
		}
		return instance;
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
