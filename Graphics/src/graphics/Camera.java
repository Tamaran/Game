package graphics;

import static org.lwjgl.opengl.GL11.*;

import mymath.Constants;
import mymath.Vector3;

import org.lwjgl.util.glu.GLU;

/**
 * A Camera that can be move freely. Contains a Cameralight that is disabled by default.
 * 
 * @author Tamaran
 *
 */
public class Camera {
	
	private float speed = 0.05f, rotSpeed = 0.02f;
	/**
	 * Position vector
	 */
	private Vector3 p = new Vector3(0,0,0);
	private Vector3 sight = new Vector3(0,0,-1);
	private Light l = new Light();

	public void moveLeft(){
		Vector3 v = sight.rotateBodyY(Constants.THREEHALFPI).mult(speed);
		p.add(v);
	}
	
	public void moveRight(){
		Vector3 v = sight.rotateBodyY(Constants.HALFPI).mult(speed);
		p.add(v);
	}
	
	public void moveForward(){
		Vector3 v = sight.mult(speed);
		p.add(v);
	}
	
	public void moveBack(){
		Vector3 v = sight.mult(speed).inverse();
		p.add(v);
	}

	/**
	 * Sets the cameraview
	 */
	public void setView(){
		sight.norm();		//to prevent rounding errors from rotating
		l.setPosition(p.x, p.y, p.z);
		Vector3 t = sight.add(p);
		GLU.gluLookAt(p.x, p.y, p.z, 
					  t.x, t.y, t.z, 
					  0, 1, 0);
	}
	
	public Light getLight(){
		return l;
	}

	public void rotateRight() {
		sight.rotateBodyY(rotSpeed);
	}

	public void rotateLeft() {
		sight.rotateBodyY(-rotSpeed);
	}

	public void rotateDown() {
		sight.rotateBodyZ(rotSpeed);
	}

	public void rotateUp() {
		sight.rotateBodyZ(-rotSpeed);
	}

	public Vector3 getPosition() {
		return p;
	}

	public Vector3 getSight() {
		return sight;
	}

	public void setSight(Vector3 sight) {
		this.sight = sight;
	}

	public void setPosition(Vector3 p) {
		this.p = p;
	}
	
	
}
