package blatt8;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import blatt8.gen.CorbaForumViewPOA;
import blatt8.gen.PositionedAvatar;
import forum.framework.IForumView;
import forum.framework.Position;

public class CorbaViewReceiver extends CorbaForumViewPOA {
	private IForumView view;
	
	public CorbaViewReceiver(IForumView p1) {
		view = p1;
	}

	@Override
	public void notifyView(PositionedAvatar[] folks) {
		Map<String,Position> map = new HashMap<String, Position>();
		for(PositionedAvatar p:folks){
			map.put(p.name, Position.getPosition(p.position.x, p.position.y));
		}
		try {
			System.out.println(map);
			view.notifyView(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
