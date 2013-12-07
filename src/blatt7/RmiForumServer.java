package blatt7;

import forum.framework.ForumServer;

public class RmiForumServer {

	public static void main(String[] args) {
		try {
			new ForumServer(RmiModelReceiver.getInstance()).run();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
