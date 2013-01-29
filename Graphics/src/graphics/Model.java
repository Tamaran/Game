package graphics;

import graphics.face.Polygon;
import graphics.materials.Material;


import mymath.depCube;
import mymath.Vector3;


import static org.lwjgl.opengl.GL11.*;


public class Model implements Renderable {

    private Polygon[] faces;
    private Vector3 pos, size, moveDir;
    private float scale;
    private float speed;

    public Model(Polygon[] faces, Vector3 pos, float scale) {
        super();
        this.faces = faces;
        this.scale = scale;
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
        glTranslatef(pos.getX(), pos.getY(), pos.getZ());
        //TODO rotation
        //glRotatef((float) Math.toDegrees(rh), 0, 1, 0);
        //glRotatef((float) Math.toDegrees(-rv), 0, 0, 1);
        glScalef(scale, scale, scale);
        Material m = null;
        for (Polygon a : faces) {
            if (m != a.getMaterial()) {
                m = a.getMaterial();
                m.apply();
            }
            a.render();
        }
        glPopMatrix();

    }
    
    

    public Vector3 getPosition() {
        return pos;
    }

    public Vector3 getSize() {
        return size;
    }

    public Vector3 getMoveDir() {
        return moveDir;
    }

    public float getScale() {
        return scale;
    }

    public float getSpeed() {
        return speed;
    }
    
    /**
     * If the edges of the boundary shall be rendered.
     *
     * @return
     */
    public boolean getRenderBox() {
        return false;
    }

    @Override
    public depCube getBounds() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
