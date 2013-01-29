package graphics.textures;

import graphics.GLUtility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;

/**
 * A Entity class representing a 2D-RGB-Texture. 
 * 
 * @author Tamaran
 *
 */
public class Texture {

	private int id, w, h;

	/**
	 * Reads a Texture from the given file. The fileformat may be any of those that are supported by the Java-API.
	 * The Texture is converted to RGB in any case.
	 * 
	 * @param s the filename
	 * @throws IOException
	 */
	public Texture(File f) throws IOException {
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		
		BufferedImage img = GLUtility.loadImage(f, BufferedImage.TYPE_INT_RGB);
		w = img.getWidth();
		h = img.getHeight();
		ByteBuffer buf = GLUtility.toByteBuffer(img);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, w, h, 0, GL_RGB, GL_UNSIGNED_BYTE, buf);
	}

	/**
	 * Sets the Texture as current
	 */
	public void apply() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	/**
	 * @return the image width
	 */
	public int getW() {
		return w;
	}

	/**
	 * @return the image height
	 */
	public int getH() {
		return h;
	}

	
}
