package graphics;

import mymath.Vector3;
import graphics.textures.Texture;
import static org.lwjgl.opengl.GL11.*;

public class Billboard {
	
	private float w,h,tx1,ty1,tx2,ty2;
	private Texture t;
	private Vector3 p, to;
	
	public Billboard(float w, float h) {
		super();
		this.w = w;
		this.h = h;
	}

	public void render(){

		Vector3 d = new Vector3(to).sub(p);
		glColor4f(1f,1f,1f,0f);
		t.apply();
		glPushMatrix();
			glTranslatef(p.getX(), p.getY(), p.getZ());
			doRotation(d);
			glBegin(GL_QUADS);
			glNormal3f(1,0,0);
			if(t != null)
				renderWithTexture();
			else
				renderWithoutTexture();
			glEnd();
		glPopMatrix();
	}
	
	public void setPostion(Vector3 p){
		this.p = p;
	}
	
	private void renderWithTexture(){

		float w2 = w/2;
		float h2 = h/2;
		glTexCoord2f(tx2,ty2);
		glVertex3f(0, -h2, w2);	
		glTexCoord2f(tx1,ty2);
		glVertex3f(0, -h2, -w2);
		glTexCoord2f(tx1,ty1);
		glVertex3f(0, h2, -w2);
		glTexCoord2f(tx2,ty1);
		glVertex3f(0, h2, w2);
	}
	
	private void renderWithoutTexture(){
		float w2 = w/2;
		float h2 = h/2;
		glVertex3f(0, -h2, w2);
		glVertex3f(0, -h2, -w2);
		glVertex3f(0, h2, -w2);
		glVertex3f(0, h2, w2);
	}
	
	private void doRotation(Vector3 d){
		d.norm();
		float[] r = d.asYZRotation();
		glRotatef((float)Math.toDegrees(r[0]), 0, -1, 0);
		glRotatef((float)Math.toDegrees(r[1]), 0, 0, 1);
	}

	public void setSize(float w, float h){
		this.w = w;
		this.h = h;
	}
	
	public void setTexture(Texture t){
		this.t = t;
	}
	
	/**
	 * 
	 * @param x1 left
	 * @param y1 bot
	 * @param x2 right
	 * @param y2 top
	 */
	public void setTextureCoords(float x1, float y1, float x2, float y2){
		this.tx1 = x1;
		this.ty1 = y1;
		this.tx2 = x2;
		this.ty2 = y2;
	}

	public Vector3 getTo() {
		return to;
	}

	public void setTo(Vector3 to) {
		this.to = to;
	}

    public void setFrom(Vector3 v) {
        p = v;
    }
	
}
