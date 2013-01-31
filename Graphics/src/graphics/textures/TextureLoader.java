package graphics.textures;

import graphics.GLUtility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import util.LazyLoader;
import util.Logger;
import util.ResourceLoader;

/**
 * Loads Textures and holds them.
 *
 * @author Tamaran
 *
 */
public class TextureLoader extends LazyLoader<String, Texture>{

    private ResourceLoader resLoader;

    public TextureLoader(ResourceLoader resLoader) {
        this.resLoader = resLoader;
    }

    @Override
    protected Texture read(String name) {
        return new Texture(GLUtility.loadImage(resLoader.getFile(name), BufferedImage.TYPE_INT_RGB));
    }
}
