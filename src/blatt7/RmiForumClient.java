package blatt7;

import forum.framework.ForumClient;

public class RmiForumClient {
	public static void main(String[] args) {
		try {
			new ForumClient(new RmiModelForwarder()).register();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
