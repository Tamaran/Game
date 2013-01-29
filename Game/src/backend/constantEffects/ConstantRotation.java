package backend.constantEffects;

import backend.Entity;

public class ConstantRotation implements ConstantEffect{
	
	private float rv,rh;
	private Entity e;
	
	/**
	 * Applys the given rotation to the entity at each update.
	 * 
	 * @param e
	 * @param rv
	 * @param rh
	 */
	public ConstantRotation(Entity e, float rv, float rh){
		this.rv = rv;
		this.rh = rh;
		this.e = e;
	}

	@Override
	public boolean apply() {

		e.rotateHorizontal(rh);
		e.rotateVertical(rv);
		return false;	//never ends
	}
	
	

}
