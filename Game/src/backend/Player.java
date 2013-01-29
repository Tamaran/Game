package backend;

import mymath.Constants;
import mymath.Vector3;
import graphics.ModelLoader;
import graphics.Renderable;

public class Player extends Entity{
	
	private static final String fname = "Fighter.obj";

	private static final float VEL = 0.1f, 
							   MAXSPEED = 1f,
							   SHIFTSPEED = 1,
							   MAXROT = (float) (Math.PI/2-0.1f);
	
	public Player() {
		super(ModelLoader.loader.getModel(fname));
	}

	public void rotateLeft(float f){
		rh += f;
	}
	
	public void rotateRight(float f){
		rh -= f;
	}
	
	public void rotateUp(float f){
		if(rv-f < -MAXROT)
			return;
		rv -= f;
	}
	
	public void rotateDown(float f){
		if(rv+f > MAXROT)
			return;
		rv += f;
	}
	
	public void speedUp(){
		speed += VEL;
		if(speed > MAXSPEED)
			speed = MAXSPEED;
	}
	
	public void slowDown(){
		speed -= VEL;
		if(speed < -MAXSPEED)
			speed = -MAXSPEED;
	}

	@Override
	public float getScale() {
		return 0.01f;
	}

	public void moveLeft() {
		bounds.getPosition().add(getDir().rotateBodyY(Constants.THREEHALFPI).mult(SHIFTSPEED));
	}

	public void moveRight() {
		bounds.getPosition().add(getDir().rotateBodyY(Constants.HALFPI).mult(SHIFTSPEED));
		
	}

	public Vector3 getPosition() {
		return new Vector3(bounds.getPosition());
	}
	
	public void die(){
		game.lose();
	}
	
	public boolean collisionWith(Entity e){
		die();
		return true;
	}

	@Override
	public String toString() {
		return "Player [bounds=" + bounds + ", rv=" + rv + ", rh=" + rh
				+ ", speed=" + speed + "]";
	}
	
	
}
