/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymath;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Tamaran
 */
public class Cube {

    public static void render(Vector3 position, Vector3 size) {
        
        float x1 = position.x - size.x / 2;
        float y1 = position.y - size.y / 2;
        float z1 = position.z - size.z / 2;
        float x2 = x1 + size.x;
        float y2 = y1 + size.y;
        float z2 = z1 + size.z;
        glColor3f(1, 0, 0);
        glBegin(GL_LINES);
        //front
        glVertex3f(x1, y1, z1);
        glVertex3f(x2, y1, z1);

        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y2, z1);

        glVertex3f(x2, y2, z1);
        glVertex3f(x1, y2, z1);

        glVertex3f(x1, y2, z1);
        glVertex3f(x1, y1, z1);
        //back
        glVertex3f(x1, y1, z2);
        glVertex3f(x2, y1, z2);

        glVertex3f(x2, y1, z2);
        glVertex3f(x2, y2, z2);

        glVertex3f(x2, y2, z2);
        glVertex3f(x1, y2, z2);

        glVertex3f(x1, y2, z2);
        glVertex3f(x1, y1, z2);
        //the rest
        glVertex3f(x1, y1, z1);
        glVertex3f(x1, y1, z2);

        glVertex3f(x1, y2, z1);
        glVertex3f(x1, y2, z2);

        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y1, z2);

        glVertex3f(x2, y2, z1);
        glVertex3f(x2, y2, z2);
        glEnd();
    }
}
