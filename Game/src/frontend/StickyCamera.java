package frontend;

import mymath.Constants;
import mymath.Vector3;
import backend.Entity;
import graphics.Camera;

public class StickyCamera extends Camera{

	private static final float BEHIND = 10,
							   TOP = 2;
	
	private Entity e;
	
	public void stick(Entity e){
		this.e = e;
	}
	
	/**
	 * Sets the camera position behind the entity and looking in the same direction.
	 */
	public void setView(){

		Vector3 cp = new Vector3(e.getBounds().getPosition());
		Vector3 viewDir = new Vector3(e.getDir());
		Vector3 tmp = new Vector3(viewDir);
		tmp.inverse();
		tmp.mult(BEHIND);
		cp.add(tmp);
		tmp.set(viewDir);
		tmp.rotateBodyZ(Constants.THREEHALFPI);
		tmp.mult(TOP);
		cp.add(tmp);
		this.setPosition(cp);
		this.setSight(viewDir);

		super.setView();
	}

	@Override
	public void moveLeft() {}

	@Override
	public void moveRight() {}

	@Override
	public void moveForward() {}

	@Override
	public void moveBack() {}

	@Override
	public void rotateRight() {}

	@Override
	public void rotateLeft() {}

	@Override
	public void rotateDown() {}

	@Override
	public void rotateUp() {}

	
}
