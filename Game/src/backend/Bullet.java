package backend;

import mymath.depCube;
import mymath.Vector3;
import graphics.GLColor;
import graphics.SphereModel;
import graphics.materials.Material;

/**
 * A Bullet that is shot by the Player.
 */
public class Bullet extends Entity {

	private static final int LIFETIME = 100;		//The amount of updates that the bullet lives
	
	private int updates;
	
	public Bullet() {
		super(new SphereModel(new Material(GLColor.RED, GLColor.RED, GLColor.RED, GLColor.RED, null), 32, 32));
	}

	public float getScale() {
		return 0.2f;
	}

	public boolean getRenderBox(){
		return false;
	}
	
	public void doLogic(){
		super.doLogic();
		updates++;
		if(updates > LIFETIME)
			die();
	}
	
	public boolean collisionWith(Entity e){
		return true;
	}
	
	protected depCube getExplosionBounds(){
		depCube c = new depCube(this.bounds);
		c.scale(10);
		return c;
	}
}