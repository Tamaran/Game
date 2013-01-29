package graphics.materials;

import graphics.textures.Texture;
import graphics.textures.TextureLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A Loader class that reads .mtl files and holds the Material Objects.
 *
 * @author Tamaran
 *
 */
public class MaterialLoader {

    public static final MaterialLoader loader = new MaterialLoader();
    private static final float SWOBJTOGL = 128f / 1000f;		//Factor for conversion of shininess from .obj format to opengl format 
    private HashMap<File, Material> data = new HashMap();
    private Material m;

    /**
     * Returns the Material with the given name and null if it didnt got
     * imported.
     *
     * @param name
     * @return
     */
    public Material getMaterial(File name) {
        return data.get(name);
    }

    /**
     * Reads the Material data from a .mtl file
     *
     * @param s
     * @throws FileNotFoundException
     */
    public void importLib(File f) throws FileNotFoundException {
        m = null;
        try (Scanner in = new Scanner(f)) {
            while (in.hasNextLine()) {
                parseLine(in.nextLine().toLowerCase());
            }
        }
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
        return TextureLoader.loader.getTexture(new File(splitAtFirstSpace(s)));
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
        this.data.put(new File(splitAtFirstSpace(line)), m);
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
