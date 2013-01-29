package mymath;

import static org.lwjgl.opengl.GL11.*;

/**
 * Entity class that Represents a Cube with position and size.
 * 
 * The Position vector points to the mid of the cube.
 * 
 * @author Tamaran
 *
 */
public class depCube {

	private Vector3 position;
	private float width,height,depth;
	
	public depCube(){}
	
	public depCube(depCube o){
		width = o.width;
		height = o.height;
		depth = o.depth;
		position = new Vector3(o.position);
	}
	
	public depCube(float x, float y, float z, float w, float h, float d){
		position = new Vector3(x,y,z);
		width = w;
		height = h;
		depth = d;
	}
	
	public Vector3 getPosition() {
		return position;
	}
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getDepth() {
		return depth;
	}
	public void setDepth(float depth) {
		this.depth = depth;
	}

	public static depCube fit(float l, float r, float b, float t, float n, float f) {

		depCube c = new depCube();
		
		c.position = new Vector3((l+r)/2, (t+b)/2, (n+f)/2);
		c.width = r-l;
		c.height = t-b;
		c.depth = f-n;
		
		return c;
	}

	public void scale(float s) {
		width *= s;
		height *= s;
		depth *= s;
	}

	public void renderBox() {

		float x1 = position.getX()-width/2;
		float y1 = position.getY()-height/2;
		float z1 = position.getZ()-depth/2;
		float x2 = x1+width;
		float y2 = y1+height;
		float z2 = z1+depth;
		glColor3f(1,0,0);
		glBegin(GL_LINES);
			//front
			glVertex3f(x1,y1,z1);
			glVertex3f(x2,y1,z1);
			
			glVertex3f(x2,y1,z1);
			glVertex3f(x2,y2,z1);
			
			glVertex3f(x2,y2,z1);
			glVertex3f(x1,y2,z1);
			
			glVertex3f(x1,y2,z1);
			glVertex3f(x1,y1,z1);
			//back
			glVertex3f(x1,y1,z2);
			glVertex3f(x2,y1,z2);
			
			glVertex3f(x2,y1,z2);
			glVertex3f(x2,y2,z2);
			
			glVertex3f(x2,y2,z2);
			glVertex3f(x1,y2,z2);
			
			glVertex3f(x1,y2,z2);
			glVertex3f(x1,y1,z2);
			//the rest
			glVertex3f(x1,y1,z1);
			glVertex3f(x1,y1,z2);
			
			glVertex3f(x1,y2,z1);
			glVertex3f(x1,y2,z2);
			
			glVertex3f(x2,y1,z1);
			glVertex3f(x2,y1,z2);
			
			glVertex3f(x2,y2,z1);
			glVertex3f(x2,y2,z2);
		glEnd();
	}

	@Override
	public String toString() {
		return "Cube [position=" + position + ", width=" + width + ", height="
				+ height + ", depth=" + depth + "]";
	}

}
