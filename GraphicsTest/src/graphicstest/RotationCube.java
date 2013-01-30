/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import graphics.Camera;
import graphics.gui.BasicFrame;
import java.util.logging.Logger;
import mymath.Cube;
import mymath.Vector3;

/**
 *
 * @author Tamaran
 */
public class RotationCube extends BasicFrame{
    
    private static final float ROT = 0.01f;

    private Vector3 p = new Vector3(0, 0, 0);
    private Vector3 s = new Vector3(2,2,2);

    @Override
    public void init() {
        super.init();
        Camera c = this.getCamera();
        c.setPosition(new Vector3(0, 10, 10));
        c.setSight(new Vector3(0, -1, -1).norm());
    }
    
    @Override
    public void render() {
        super.render();
        Camera c = this.getCamera();
        c.setPosition(c.getPosition().rotateY(ROT));
        c.setSight(c.getSight().rotateY(ROT));
        Cube.renderCube(p, s);
    }
    
    
    
    
}
