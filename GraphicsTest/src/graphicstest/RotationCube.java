/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import graphics.gui.BasicFrame;
import java.util.logging.Logger;
import mymath.Cube;
import mymath.Vector3;

/**
 *
 * @author Tamaran
 */
public class RotationCube extends BasicFrame{

    @Override
    public void render() {
        super.render();
        Vector3 p = new Vector3(0, 0, 5);
        Vector3 s = new Vector3(1,1,1);
        Cube.render(p, s);
    }
    
    
    
    
}
