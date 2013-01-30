package graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import mymath.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

public class GLUtility {

    public static float VISION_NEAR = .1f, VISION_FAR = 10000;
    private static int REDMASK = getRedMask(), BLUEMASK = getBlueMask(), GREENMASK = getGreenMask();

    public static void destroyOpengl() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }

    /**
     * Initializes the display, keyboard and the mouse. All necessary settings a
     * set too.
     *
     * The display is set to Windowed mode and sized to full Screen size.
     *
     * @throws LWJGLException
     */
    public static boolean initOpengl() {
        try {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Display.setDisplayMode(new DisplayMode(d.width, d.height));

            //init opengl
            Display.create();
            Keyboard.create();
            Mouse.create();
            // Depth test setup
            GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
            GL11.glDepthFunc(GL11.GL_LEQUAL);  // The Type Of Depth Testing To Do

            // Some basic settings
            GL11.glClearColor(0f, 0f, 0f, 1f); // Black Background
            GL11.glEnable(GL11.GL_NORMALIZE);  // force normal lengths to 1
            GL11.glEnable(GL11.GL_CULL_FACE);  // don't render hidden faces
            GL11.glEnable(GL11.GL_TEXTURE_2D); // use textures
            GL11.glEnable(GL11.GL_BLEND);      // enable transparency
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
            GL11.glShadeModel(GL11.GL_SMOOTH);

            // How to handle transparency: average colors together
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            // Enable alpha test so the transparent backgrounds in texture images don't draw.
            // This prevents transparent areas from affecting the depth or stencil buffer.
            // alpha func will accept only fragments with alpha greater than 0
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0f);

            // Draw specular highlghts on top of textures
            GL11.glLightModeli(GL12.GL_LIGHT_MODEL_COLOR_CONTROL, GL12.GL_SEPARATE_SPECULAR_COLOR);

            // Perspective quality
            GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);

            // Set the size and shape of the screen area
            //GL11.glViewport(0, 0, d.width, d.height);
            resize(d.width, d.height);

            // select model view for subsequent transformations
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*    public static void setPerspective()
     {
     DisplayMode m = Display.getDisplayMode();
     // select projection matrix (controls perspective)
     GL11.glMatrixMode(GL11.GL_PROJECTION);
     GL11.glLoadIdentity();
     GLU.gluPerspective(40f, m.getWidth()/m.getHeight(), VISION_NEAR, VISION_FAR);
     // return to modelview matrix
     GL11.glMatrixMode(GL11.GL_MODELVIEW);
     }
	
     public static void setOrtho()
     {
     DisplayMode m = Display.getDisplayMode();
     // select projection matrix (controls view on screen)
     GL11.glMatrixMode(GL11.GL_PROJECTION);
     GL11.glLoadIdentity();
     // set ortho to same size as viewport, positioned at 0,0
     GL11.glOrtho(
     0,m.getWidth(),  // left,right
     0,m.getHeight(),  // bottom,top
     VISION_NEAR,VISION_FAR);    // Zfar, Znear
     // return to modelview matrix
     GL11.glMatrixMode(GL11.GL_MODELVIEW);
     }*/
    /**
     * Changes the screen size to the given size. Changes the MatrixMode to
     * GL_MODELVIEW
     *
     * @param w
     * @param h
     */
    public static void resize(int w, int h) {

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glViewport(0, 0, w, h);
        //glOrtho(0,w,0,h,VISION_NEAR,VISION_FAR);
        GLU.gluPerspective(45f, ((float) w) / h, VISION_NEAR, VISION_FAR);
        glMatrixMode(GL_MODELVIEW);
    }

    /**
     * Changes the size to the actual Display size.
     */
    public static void resize() {
        DisplayMode m = Display.getDisplayMode();
        resize(m.getWidth(), m.getHeight());
    }

    /**
     * Resets the Buffers and sets a black background
     */
    public static void resetBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0, 0, 0, 1f);
    }

    public static ByteBuffer toByteBuffer(BufferedImage img) {
        ByteBuffer buf = directByteBuffer(img.getWidth() * img.getHeight() * 3);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                int argb = img.getRGB(j, i);
                buf.put((byte) ((argb & REDMASK) >> 16));
                buf.put((byte) ((argb & GREENMASK) >> 8));
                buf.put((byte) (argb & BLUEMASK));
            }
        }
        buf.flip();
        return buf;
    }

    /**
     * Allocates a direct FloatBuffer with native Byte Order
     *
     * @param size
     * @return
     */
    public static FloatBuffer directFloatBuffer(int size) {
        return ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    /**
     * @param a
     * @return a direct FloatBuffer containing the given float[]
     */
    public static FloatBuffer wrapFloatArray(float[] a) {
        FloatBuffer buf = directFloatBuffer(a.length);
        buf.put(a);
        buf.flip();
        return buf;
    }

    /**
     * Builds a FloatBuffer that contains (v.x, v.y, v.z, f).
     *
     * @param v
     * @param f
     * @return
     */
    public static FloatBuffer toFloatBuffer(Vector3 v, float f) {
        return asFloatBuffer(v.x, v.y, v.z, f);
    }

    /**
     * Allocates a direct ByteBuffer with native Byte Order
     *
     * @param size
     * @return
     */
    public static ByteBuffer directByteBuffer(int n) {
        return ByteBuffer.allocateDirect(n).order(ByteOrder.nativeOrder());
    }

    /**
     * Loads a BufferedImage from the given Filename and converts it to the
     * given format.
     *
     * @param fname
     * @param format
     * @return
     * @throws IOException
     */
    public static BufferedImage loadImage(File f, int format) throws IOException {
        BufferedImage img = ImageIO.read(f);
        if (img.getType() == format) {
            return img;
        }
        BufferedImage n = new BufferedImage(img.getWidth(), img.getHeight(), format);
        n.getGraphics().drawImage(img, 0, 0, null);
        return n;
    }

    /**
     * @see wrapFloatArray(float[]);
     *
     * @param args
     * @return
     */
    public static FloatBuffer asFloatBuffer(float... args) {
        return wrapFloatArray(args);
    }

    private static int getRedMask() {
        int m = 0;
        for (int i = 16; i < 24; i++) {
            m += 1 << i;
        }
        return m;
    }

    private static int getBlueMask() {
        int m = 0;
        for (int i = 0; i < 8; i++) {
            m += 1 << i;
        }
        return m;
    }

    private static int getGreenMask() {
        int m = 0;
        for (int i = 8; i < 16; i++) {
            m += 1 << i;
        }
        return m;
    }
}
