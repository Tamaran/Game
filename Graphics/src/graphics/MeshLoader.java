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
import java.util.logging.Level;
import mymath.Vector3;
import util.LazyLoader;


import util.Logger;
import util.ResourceLoader;

/**
 * Reads .obj files and holds them
 *
 * @author Tamaran
 *
 */
public class MeshLoader extends LazyLoader<String, Mesh>{

    private ResourceLoader resLoader;
    private MaterialLoader matLoader;

    public MeshLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
        matLoader = new MaterialLoader(resLoader);
    }

    @Override
    protected Mesh read(String name) {
        try {
            return new MeshLoadHelper(name, new PhongShading(), resLoader, matLoader).getMesh();
        } catch (MeshParseException | FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(MeshLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
