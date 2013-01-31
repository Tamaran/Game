package graphics.shadingModes;

import graphics.face.Polygon;
import graphics.face.Vertex;
import java.util.List;

public class FlatShading extends Shading {

    @Override
    public void calcVertexNormals(Polygon p) {
        int n = p.getVertices().size();
        for(int i = 0; i < n; i++)
        {
            p.getVertexNormals().add(p.getNormal());
        }
    }
}
