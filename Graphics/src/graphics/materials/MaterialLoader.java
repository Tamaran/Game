package graphics.materials;

import graphics.textures.Texture;
import graphics.textures.TextureLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import util.LazyLoader;
import util.ResourceLoader;

/**
 * A Loader class that reads .mtl files and holds the Material Objects.
 *
 * @author Tamaran
 *
 */
public class MaterialLoader{

    private static final float SWOBJTOGL = 128f / 1000f;		//Factor for conversion of shininess from .obj format to opengl format 
    
    private HashMap<MaterialAddress, Material> data = new HashMap();
    
    private Material m;
    private String file;
    
    private ResourceLoader resLoader;
    private TextureLoader texLoader;

    public MaterialLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
        texLoader = new TextureLoader(resLoader);
    }
    
    public void importMtl(String file) {
        this.file = file;
        try {
            try (Scanner in = new Scanner(resLoader.getFile(file))) {
                while (in.hasNextLine()) {
                    parseLine(in.nextLine().toLowerCase());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Material getMaterial(MaterialAddress address) throws MaterialNotFoundException
    {
        Material r = data.get(address);
        if(r == null)
        {
            throw new MaterialNotFoundException(address.toString());
        }
        return r;
    }
            

    private void parseLine(String line) {
        if (line.isEmpty()) {
            return;
        }
        if (line.startsWith("#")) //is comment
        {
            return;
        }
        if (line.startsWith("d ") || line.startsWith("tr ")) {
            parseTransparency(line);
        } else if (line.startsWith("ka ")) {
            parseAmbient(line);
        } else if (line.startsWith("kd ")) {
            parseDiffuse(line);
        } else if (line.startsWith("ks ")) {
            parseSpecular(line);
        } else if (line.startsWith("illum ")) {
            readIllum(line);
        } else if (line.startsWith("newmtl ")) {
            newMtl(line);
        } else if (line.startsWith("ns ")) {
            parseSpecularWeight(line);
        } else if (line.startsWith("map_ks")) {
            readKsMap(line);
        } else if (line.startsWith("map_kd")) {
            readKdMap(line);
        } else {
            System.err.println("MaterialParse: Line ignored: " + line);
        }
    }

    private void readKsMap(String line) {
        m.setMap_Ks(readMap(line));
    }

    private void readKdMap(String line) {
        m.setMap_Kd(readMap(line));
    }

    private Texture readMap(String s) {
        return texLoader.get(splitAtFirstSpace(s));
    }

    private void parseTransparency(String line) {
        String[] data = line.split(" ");
        m.setTransparency(Float.parseFloat(data[1]));
    }

    private void parseSpecularWeight(String line) {
        String[] data = line.split(" ");
        m.setSpecularWeight(convertToOpenglRange(Float.parseFloat(data[1])));
    }

    private float convertToOpenglRange(float specWeight) {
        //0 - 1000 to 0 - 128
        return specWeight * SWOBJTOGL;
    }

    private void parseSpecular(String line) {
        String[] data = line.split(" ");
        float r = Float.parseFloat(data[1]);
        float g = Float.parseFloat(data[2]);
        float b = Float.parseFloat(data[3]);
        m.setSpecular(r, g, b);
    }

    private void parseDiffuse(String line) {
        String[] data = line.split(" ");
        float r = Float.parseFloat(data[1]);
        float g = Float.parseFloat(data[2]);
        float b = Float.parseFloat(data[3]);
        m.setDiffuse(r, g, b);
    }

    private void readIllum(String line) {
        String[] data = line.split(" ");
        for (int i = 1; i < data.length; i++) {
            m.addIllumMode(Integer.parseInt(data[i]));
        }
    }

    private void newMtl(String line) {
        m = new Material();
        this.data.put(new MaterialAddress(file, splitAtFirstSpace(line)), m);
    }

    private void parseAmbient(String line) {
        String[] data = line.split(" ");
        float r = Float.parseFloat(data[1]);
        float g = Float.parseFloat(data[2]);
        float b = Float.parseFloat(data[3]);
        m.setAmbient(r, g, b);
    }

    private String splitAtFirstSpace(String s) {
        int space = s.indexOf(' ');
        if (space == -1 || space == s.length() - 1) {
            return s;
        }
        return s.substring(space + 1);
    }

}
