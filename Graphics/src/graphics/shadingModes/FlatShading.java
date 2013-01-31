package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;
import java.util.List;
import mymath.Vector3;

public class FlatShading extends Shading {

    @Override
    public void calcVertexNormals(Polygon p) {
        List<Vertex> v = p.getVertices();
    }

    @Override
    public void calcSurfaceNormal(Polygon p) {
        calcFlatSurfaceNormal(p);
    }
}
