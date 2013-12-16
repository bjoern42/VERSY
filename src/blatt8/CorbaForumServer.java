package blatt8;

import forum.framework.ForumServer;

public class CorbaForumServer {
	public static void main(String[] args) {
		try {
			new ForumServer(CorbaModelReceiver.getInstance(args)).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
