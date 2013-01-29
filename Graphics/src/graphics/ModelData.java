/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import graphics.face.Polygon;
import mymath.Vector3;

/**
 *
 * @author Tamaran
 */
public class ModelData {
    
    private Polygon[] polygons;
    private Vector3 size;

    public ModelData(Polygon[] polygons, Vector3 size) {
        this.polygons = polygons;
        this.size = size;
    }

    public Polygon[] getPolygons() {
        return polygons;
    }

    public Vector3 getSize() {
        return size;
    }
    
    
    
}
