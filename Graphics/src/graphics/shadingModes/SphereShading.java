package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;

import java.util.List;

import mymath.Vector3;

public class SphereShading extends Shading{

	@Override
	public void calcVertexNormals(Polygon p) {
		List<Vertex> vertice = p.getVertices();
		List<Vector3> normals = p.getVertexNormals();
		for(Vertex v : vertice){
			if(v.getSurfaceNormal() == null){
				Vector3 n = new Vector3(v);
				v.setSurfaceNormal(n);
			}
			Vector3 norm = v.getSurfaceNormal();
			normals.add(norm);
		}
	}

}
