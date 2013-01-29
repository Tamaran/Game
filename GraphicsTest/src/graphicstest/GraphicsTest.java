/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import graphics.GLUtility;

/**
 *
 * @author Tamaran
 */
public class GraphicsTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GLUtility.initOpengl();
        new Frame().loop();
    }
}
