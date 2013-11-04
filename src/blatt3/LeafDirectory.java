package blatt3;

public class LeafDirectory extends Directory{
	private Cell cell;
	private int entity;
	
	protected LeafDirectory(Cell cell, CompositeDirectory parent) {
		super(parent);
		this.cell = cell;
	}

	@Override
	public Cell lookup(int entity) {
		if(this.entity == entity){
			return cell;
		}
		return parent.lookup(entity);
	}

	public void insert(int entity) {
		parent.addChild(entity, this);
		this.entity = entity;
	}

}
