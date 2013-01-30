package graphics;

import graphics.face.Polygon;
import graphics.face.TextureCoodinate;
import graphics.face.Vertex;
import graphics.materials.Material;
import graphics.materials.MaterialLoader;
import graphics.shadingModes.FlatShading;
import graphics.shadingModes.PhongShading;
import graphics.shadingModes.Shading;
import graphics.shadingModes.SphereShading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import mymath.Vector3;


import util.Logger;
import util.ResourceLoader;

/**
 * Reads .obj files and holds them
 *
 * @author Tamaran
 *
 */
public class MeshLoader {

    
    private final HashMap<String, Mesh> data = new HashMap<>();

    private ResourceLoader resLoader;
    private MaterialLoader matLoader;

    public MeshLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
        matLoader = new MaterialLoader(resLoader);
    }

    /**
     * Returns the model if its loaded already, otherwise its parsed from the
     * .obj file.
     *
     * @param s
     * @return
     */
    public Mesh getMesh(String f, Shading shading) {
        Mesh m = data.get(f);
        if (m == null) {
            m = new MeshLoadHelper(f, shading, resLoader, matLoader).getMesh();
            data.put(f, m);
        }
        return m;
    }

    public Mesh getMesh(String s) {
        return getMesh(s, new PhongShading());
    }


}
