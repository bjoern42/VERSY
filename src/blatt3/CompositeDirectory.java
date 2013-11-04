package blatt3;

import java.util.Map;
import java.util.TreeMap;


public class CompositeDirectory extends Directory{
	private Map<Integer, Directory> children = new TreeMap<Integer, Directory>();
	
	protected CompositeDirectory(CompositeDirectory parent) {
		super(parent);
	}

	@Override
	public Cell lookup(int entity) {
		Directory leaf = children.get(entity);
		if(leaf == null){
			return parent.lookup(entity);
		}
		return leaf.lookup(entity);
	}
	
	public void addChild(int entity, Directory directory){
		children.put(entity, directory);
		if(parent != null){
			parent.addChild(entity, this);
		}
	}

}
