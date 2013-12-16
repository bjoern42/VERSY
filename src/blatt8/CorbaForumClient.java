package blatt8;

import forum.framework.ForumClient;

public class CorbaForumClient {
	public static void main(String[] args) {
		try {
			new ForumClient(new CorbaModelForwarder(args)).register();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
