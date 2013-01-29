package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;

import java.util.List;

import mymath.Vector3;

public class FlatShading implements Shading{
	
	/**
	 * Caluclates the surface normal
	 */
	public void calcSurfaceNormal(Polygon p){
		List<Vertex> v = p.getVertices();
		Vertex o = v.get(0);
		Vertex v1 = v.get(1);
		Vertex v2 = v.get(2);
		Vector3 origin = new Vector3(o.getX(), o.getY(), o.getZ());
		Vector3 a = new Vector3(v1.getX(), v1.getY(), v1.getZ());
		Vector3 b = new Vector3(v2.getX(), v2.getY(), v2.getZ());
		a.sub(origin);
		b.sub(origin);
		Vector3 normal = a.cross(b).norm();
		p.setNormal(normal);
	}
	
	public void calcVertexNormals(Polygon p){
		List<Vertex> v = p.getVertices();
	}

}
