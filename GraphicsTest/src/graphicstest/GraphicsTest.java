/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import graphics.GLUtility;
import org.lwjgl.LWJGLException;

/**
 *
 * @author Tamaran
 */
public class GraphicsTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LWJGLException
    {
        GLUtility.initOpengl();
        new BasicFrameTest().loop();
    }
}
