package collisions;

import java.util.List;

import mymath.Vector3;
import backend.Entity;
import graphics.face.Polygon;
import graphics.face.Vertex;
//TODO
public class PolygonCollision implements CollisionChecker{

	public boolean intersect(Polygon p1, Polygon p2){
		
		if(checkForIntersect(p1,p2))
			return true;
		if(checkForIntersect(p2,p1))
			return true;
		return false;
	}
	
	private boolean checkForIntersect(Polygon p1, Polygon p2){
		
		List<Vertex> vl1 = p1.getVertices();
		List<Vertex> vl2 = p2.getVertices();
		float[] vert1 = new float[vl1.size()];
		float[] vert2 = new float[vl2.size()];
		for(Vertex v : p1.getVertices()){
			Vector3 axis = new Vector3(v.getNormal());
			axis.rotateBodyY((float) (Math.PI/2));
			axis.norm();
			for(int i = 0; i < vl1.size(); i++){
				Vertex a = vl1.get(i);
				vert1[i] = a.getX()*axis.getX()+a.getY()*axis.getY()+a.getZ()*axis.getZ();
			}
			for(int i = 0; i < vl2.size(); i++){
				Vertex a = vl1.get(i);
				vert2[i] = a.getX()*axis.getX()+a.getY()*axis.getY()+a.getZ()*axis.getZ();
			}
			float[] v1b = getBounds(vert1);
			float[] v2b = getBounds(vert2);
			if(v1b[1] > v2b[0])		
				if(v1b[0] < v2b[1])
					return true;
		}
		return false;
	}
	
	private float[] getBounds(float[] a){
		float[] r = new float[2];
		r[0] = Float.MAX_VALUE;
		r[1] = Float.MIN_VALUE;
		for(float f : a){
			if(f < r[0])
				r[0] = f;
			if(f > r[1])
				r[1] = f;
		}
		return r;
	}
	
	public boolean collided(Entity e1, Entity e2){
		return false;
	}
}
