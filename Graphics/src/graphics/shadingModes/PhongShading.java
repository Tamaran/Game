package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;

import java.util.List;

import mymath.Vector3;

public class PhongShading implements Shading{

	@Override
	public void calcSurfaceNormal(Polygon p) {
		new FlatShading().calcSurfaceNormal(p);
	}

	@Override
	public void calcVertexNormals(Polygon p) {
		List<Vector3> normals = p.getVertexNormals();
		List<Vertex> vert = p.getVertices();
		for(Vertex v : vert){
			Vector3 n = new Vector3();
			List<Polygon> neig = v.getFaces();
			for(Polygon a : neig)
				n.add(a.getNormal());
			n.norm();
			normals.add(n);
		}
	}

}
