package graphics.shadingModes;

import graphics.face.Polygon;

public interface Shading {

	public void calcSurfaceNormal(Polygon p);
	public void calcVertexNormals(Polygon p);
	
}
