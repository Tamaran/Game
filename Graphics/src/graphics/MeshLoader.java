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

    private static final int TYPE_VFACE = 1, TYPE_VTFACE = 2, TYPE_VTNFACE = 3, TYPE_VNFACE = 4;
    private static final float MIN = Float.MIN_VALUE, MAX = Float.MAX_VALUE;
    private final HashMap<String, Mesh> data = new HashMap<>();
    //Variables that are used in reading process
    private Material selMat;
    private List<Vertex> v = getList();
    private List<Polygon> f = getList();
    private List<TextureCoodinate> vt = getList();
    private float left, right, top, bot, near, far;
    private Shading shading;
    private ResourceLoader resLoader;
    private MaterialLoader matLoader;

    public MeshLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
        matLoader = new MaterialLoader(resLoader);
        clearAll();
    }

    /**
     * Returns the model if its loaded already, otherwise its parsed from the
     * .obj file.
     *
     * @param s
     * @return
     */
    public Mesh getMesh(String f, Shading shading) {
        this.shading = shading;
        Mesh m = data.get(f);
        if (m == null) {
            m = readMesh(f);
            data.put(f, m);
        }
        return m;
    }

    public Mesh getMesh(String s) {
        return getMesh(s, new PhongShading());
    }

    private Mesh readMesh(String file) {
        try {
            long t = System.currentTimeMillis();
            selMat = Material.WHITE;
            try (Scanner in = new Scanner(resLoader.getFile(file))) {
                while (in.hasNextLine()) {
                    readLine(in.nextLine());
                }
            }
            Mesh m = new Mesh(toArray(f), new Vector3(right - left, top - bot, near - far));
            centerVertices();
            long normTime = System.currentTimeMillis();
            calcNormals();
            Logger.D("Calculating Normals for " + file + " finished after " + (System.currentTimeMillis() - normTime) + "ms");
            clearAll();
            Logger.D("Loading " + file + " finished after " + (System.currentTimeMillis() - t) + "ms");
            return m;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void calcNormals() {
        for (Polygon p : f) {
            shading.calcSurfaceNormal(p);
        }
        for (Polygon p : f) {
            shading.calcVertexNormals(p);
        }
    }

    private void readLine(String line) {
        line = line.trim().toLowerCase();
        if (line.isEmpty() || line.charAt(0) == '#') //is comment
        {
            return;
        }
        if (line.startsWith("f ")) {
            readFace(line);
        } else if (line.startsWith("v ")) {
            readVertice(line);
        } else if (line.startsWith("vt ")) {
            readTextureCood(line);
        } else if (line.startsWith("vn ")) {
            ;//normals are ignored
        } else if (line.startsWith("usemtl ")) {
            readUseMTL(line);
        } else if (line.startsWith("mtllib ")) {
            readImport(line);
        } else {
            Logger.W("MeshLoader: line not parsed: " + line);
        }

    }

    private void readImport(String line) {
        String s = splitAtFirstSpace(line);
        s = toJavaPath(s);
        matLoader.importLib(s);
    }

    private void readUseMTL(String line) 
    {
        selMat = matLoader.getMaterial(splitAtFirstSpace(line));
    }

    private void readVertice(String line) {
        String[] data = line.split(" ");
        float x = Float.parseFloat(data[1]);
        float y = Float.parseFloat(data[2]);
        float z = Float.parseFloat(data[3]);
        float w = 1f;
        if (data.length == 5) {
            w = Float.parseFloat(data[4]);
        }
        v.add(new Vertex(x, y, z, w));
        //update bounds
        if (x < left) {
            left = x;
        }
        if (x > right) {
            right = x;
        }
        if (y < bot) {
            bot = y;
        }
        if (y > top) {
            top = y;
        }
        if (z < near) {
            near = z;
        }
        if (z > far) {
            far = z;
        }
    }

    private void readFace(String line) {
        String[] data = line.split(" ");
        Polygon face = null;
        switch (getFaceType(data[1])) {
            case TYPE_VFACE:
                face = readVFace(data);
                break;
            case TYPE_VTFACE:
                face = readVTFace(data);
                break;
            case TYPE_VTNFACE:
                face = readVTNFace(data);
                break;
            case TYPE_VNFACE:
                face = readVNFace(data);
                break;
        }
        face.setMaterial(selMat);
        face.setShadingMode(shading);
        f.add(face);
    }

    private Polygon readVFace(String[] data) {
        Polygon face = new Polygon();
        for (int i = 1; i < data.length; i++) {
            Vertex a = v.get(Integer.parseInt(data[i]) - 1);
            face.addVertex(a);
            a.setPolygon(face);
        }
        return face;
    }

    private Polygon readVNFace(String[] data) {

        Polygon face = new Polygon();
        int l = data.length;
        for (int i = 1; i < l; i++) {
            String[] p = data[i].split("//");
            Vertex a = v.get(Integer.parseInt(p[0]) - 1);
            face.addVertex(a);
            a.setPolygon(face);
        }
        return face;
    }

    private Polygon readVTFace(String[] data) {
        Polygon face = new Polygon();
        int l = data.length;
        for (int i = 1; i < l; i++) {
            String[] p = data[i].split("/");
            Vertex a = v.get(Integer.parseInt(p[0]) - 1);
            face.addVertex(a);
            a.setPolygon(face);
            face.addTexCood(vt.get(Integer.parseInt(p[1]) - 1));
        }
        return face;
    }

    private Polygon readVTNFace(String[] data) {
        Polygon face = new Polygon();
        int l = data.length;
        for (int i = 1; i < l; i++) {
            String[] p = data[i].split("/");
            Vertex a = v.get(Integer.parseInt(p[0]) - 1);
            face.addVertex(a);
            a.setPolygon(face);
            face.addTexCood(vt.get(Integer.parseInt(p[1]) - 1));
        }
        return face;
    }

    private void readTextureCood(String line) {
        String[] data = line.split(" ");
        float x = Float.parseFloat(data[1]);
        float y = Float.parseFloat(data[2]);
        float w = 1f;
        if (data.length == 4) {
            w = Float.parseFloat(data[3]);
        }
        vt.add(new TextureCoodinate(x, y, w));
    }

    private static List getList() {
        return new ArrayList();
    }

    private int getFaceType(String s) {
        int l = s.length();
        int n = 0;
        for (int i = 0; i < l && n < 2; i++) {
            if (s.charAt(i) == '/') {
                n++;
                if (i + 1 < l && s.charAt(i + 1) == '/') {
                    return TYPE_VNFACE;
                }
            }
        }
        switch (n) {
            case 0:
                break;
            case 1:
                return TYPE_VTFACE;
            case 2:
                return TYPE_VTNFACE;
            default:
                System.err.println("Bad Face Type: " + s);
        }
        return TYPE_VFACE;
    }

    private String splitAtFirstSpace(String s) {
        int space = s.indexOf(' ');
        if (space == -1 || space == s.length() - 1) {
            return s;
        }
        return s.substring(space + 1);
    }

    private String toJavaPath(String s) {
        if (s.startsWith("./")) {
            return s.substring(2);
        }
        return s;
    }

    private void clearAll() {
        v.clear();
        vt.clear();
        f.clear();
        left = MAX;
        right = MIN;
        top = MIN;
        bot = MAX;
        near = MAX;
        far = MIN;
    }

    private Polygon[] toArray(List<Polygon> l) {
        Polygon[] d = new Polygon[l.size()];
        l.toArray(d);
        return d;
    }

    /**
     * Moves the Vertices so that all are inside of the bounding cube
     */
    private void centerVertices() {
        float x = -(left + right) / 2;
        float y = -(bot + top) / 2;
        float z = -(near + far) / 2;

        for (Vertex a : v) {
            a.move(x, y, z);
        }
        left += x;
        right += x;
        top += y;
        bot += y;
        near += z;
    }
}
