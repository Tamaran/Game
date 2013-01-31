package graphics.face;

import java.util.ArrayList;
import java.util.List;

import mymath.Vector3;

public class Vertex {

	private float x,y,z,w;
	private List<Polygon> faces = new ArrayList();
	private Vector3 surfaceNormal;

	public Vertex(float x, float y, float z, float w) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public Vertex() {
		super();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public void setPolygon(Polygon face) {
		faces.add(face);
	}

	public void move(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public Vector3 getSurfaceNormal() {
		return surfaceNormal;
	}

	public List<Polygon> getFaces() {
		return faces;
	}

	public void setSurfaceNormal(Vector3 surfaceNormal) {
		this.surfaceNormal = surfaceNormal;
	}	

}
