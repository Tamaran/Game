package graphics;

import graphics.materials.MaterialLoader;
import graphics.shadingModes.FlatShading;
import graphics.shadingModes.PhongShading;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import util.LazyLoader;


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
