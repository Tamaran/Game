package graphics;

import graphics.face.Polygon;
import graphics.materials.Material;

import mymath.Vector3;


import static org.lwjgl.opengl.GL11.*;


public class Model implements Renderable {

    private Mesh mesh;
    private Vector3 pos, moveDir;
    private float speed;

    public Model(Mesh mesh, Vector3 pos) {
        super();
        this.mesh = mesh;
        this.pos = pos;
    }
    
    public void update(long time)
    {
        pos = pos.add(moveDir.mult(speed*time));
    }

    @Override
    public void render() {

        if (getRenderBox()) {
            glColor3f(1, 0, 0);
            //TODO
            //bounds.renderBox();
        }
        glPushMatrix();
        glTranslatef(pos.x, pos.y, pos.z);
        //TODO rotation
        //glRotatef((float) Math.toDegrees(rh), 0, 1, 0);
        //glRotatef((float) Math.toDegrees(-rv), 0, 0, 1);
        mesh.render();
        glPopMatrix();

    }
    
    

    public Vector3 getPosition() {
        return pos;
    }

    public Vector3 getMoveDir() {
        return moveDir;
    }

    public float getSpeed() {
        return speed;
    }
    
    /**
     * If the edges of the boundary shall be rendered.
     *
     * @return
     */
    public boolean getRenderBox()
    {
        return false;
    }

}
