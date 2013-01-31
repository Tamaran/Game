package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;
import java.util.List;
import mymath.Vector3;

public class PhongShading extends Shading{

	@Override
	public void calcSurfaceNormal(Polygon p) {
		calcFlatSurfaceNormal(p);
	}

	@Override
	public void calcVertexNormals(Polygon p) {
		List<Vector3> normals = p.getVertexNormals();
		List<Vertex> vert = p.getVertices();
		for(Vertex v : vert){
			Vector3 n = Vector3.ZERO;
			List<Polygon> neig = v.getFaces();
			for(Polygon a : neig)
                        {
                            n = n.add(a.getNormal());
                        }	
			normals.add(n.norm());
		}
	}

}
