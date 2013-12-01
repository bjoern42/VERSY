package blatt6;

import forum.framework.ForumClient;
import forum.framework.ForumServer;

public class LocalForumSetup {
	public static void main(String[] args) {
		new ForumServer(LocalModelReceiver.getInstance());
		try {
			new ForumClient(new LocalModelForwarder()).register();
			new ForumClient(new LocalModelForwarder()).register();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
