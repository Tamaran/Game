package graphics;

import graphics.materials.Material;
import graphics.textures.Texture;
import graphics.textures.TextureLoader;
import java.io.File;

import java.io.IOException;

import mymath.Vector3;

import org.lwjgl.util.glu.Sphere;


import static org.lwjgl.opengl.GL11.*;

/**
 * A Background texture.
 * 
 * @author Tamaran
 *
 */
public class Background {

	private Texture tex;
	private float dist;
	
	public Background(File f, float dist){
		this.dist = dist;
		tex = TextureLoader.loader.getTexture(f);
	}

	/**
	 * Renders a sphere and paints the texture an the back side
	 * 
	 * @param camPos the Camera Position
	 */
	public void render(Vector3 camPos){
		
/*		glCullFace(GL_FRONT);
		sphere.setTextureFlag(true);
		tex.apply();
		Material.WHITE.apply();
		glPushMatrix();
		glTranslatef(camPos.getX(), camPos.getY(), camPos.getZ());
		sphere.draw(dist, 32, 32);
		glPopMatrix();
		glCullFace(GL_BACK);*/
		Material.WHITE.apply();
		tex.apply();
		glCullFace(GL_FRONT);
		glPushMatrix();
		glTranslatef(camPos.x, camPos.y, camPos.z);
		glBegin(GL_QUADS);
			glTexCoord2f(0.66f, 0.66f);
			glVertex3f(dist, -dist, -dist);
			glTexCoord2f(0.33f, 0.66f);
			glVertex3f(dist, -dist, dist);
			glTexCoord2f(0.33f, 0.33f);
			glVertex3f(-dist, -dist, dist);
			glTexCoord2f(0.66f, 0.33f);
			glVertex3f(-dist, -dist, -dist);
			
			glTexCoord2f(0.33f, 0.33f);
			glVertex3f(dist, dist, -dist);
			glTexCoord2f(0, 0.33f);
			glVertex3f(-dist, dist, -dist);
			glTexCoord2f(0, 0);
			glVertex3f(-dist, dist, dist);
			glTexCoord2f(0.33f, 0);
			glVertex3f(dist, dist, dist);
			
			glTexCoord2f(0.66f, 0.66f);
			glVertex3f(dist, -dist, -dist);
			glTexCoord2f(0.66f, 1);
			glVertex3f(dist, dist, -dist);
			glTexCoord2f(0.33f, 1);
			glVertex3f(dist, dist, dist);
			glTexCoord2f(0.33f, 0.66f);
			glVertex3f(dist, -dist, dist);
			
			glTexCoord2f(0.66f, 0);
			glVertex3f(dist, -dist, dist);
			glTexCoord2f(0.33f, 0.33f);
			glVertex3f(dist, dist, dist);
			glTexCoord2f(0.33f, 0.33f);
			glVertex3f(-dist, dist, dist);
			glTexCoord2f(0.33f, 0);
			glVertex3f(-dist, -dist, dist);
			
			glTexCoord2f(0.66f, 0.66f);
			glVertex3f(-dist, -dist, dist);
			glTexCoord2f(0.66f, 0.33f);
			glVertex3f(-dist, dist, dist);
			glTexCoord2f(1, 0.33f);
			glVertex3f(-dist, dist, -dist);
			glTexCoord2f(1, 0.66f);
			glVertex3f(-dist, -dist, -dist);
			
			glTexCoord2f(0.33f, 0.33f);
			glVertex3f(dist, dist, -dist);
			glTexCoord2f(0.33f, 0.66f);
			glVertex3f(dist, -dist, -dist);
			glTexCoord2f(0, 0.66f);
			glVertex3f(-dist, -dist, -dist);
			glTexCoord2f(0, 0.33f);
			glVertex3f(-dist, dist, -dist);
		glEnd();
		glCullFace(GL_BACK);
		glPopMatrix();
	}
}
