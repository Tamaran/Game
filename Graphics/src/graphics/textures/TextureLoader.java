package graphics.textures;

import graphics.GLUtility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import util.Logger;
import util.ResourceLoader;

/**
 * Loads Textures and holds them.
 *
 * @author Tamaran
 *
 */
public class TextureLoader {

    private HashMap<String, Texture> map = new HashMap();
    private ResourceLoader resLoader;

    public TextureLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
    }

    /**
     * Returns the Texture with the given filename. The Texture is loaded if
     * necessary.
     *
     * @param s
     * @return
     * @throws IOException
     */
    public Texture getTexture(String f) {
            Texture t = map.get(f);
            if (t == null) {
                long time = System.currentTimeMillis();
                t = new Texture(GLUtility.loadImage(resLoader.getFile(f), BufferedImage.TYPE_INT_RGB));
                Logger.D("Loading " + f + " finished after " + (System.currentTimeMillis() - time) + "ms");
                map.put(f, t);
            }
            return t;
    }
}
