package test;

import mymath.Vector3;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;


import graphics.Background;
import graphics.BasicFrame;
import graphics.Light;
import graphics.Model;
import graphics.ModelLoader;
import graphics.textures.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class BasicFrameTest extends BasicFrame{
	
	private Model cube;
	private Model ship;
	private Model earth;
	private Model sun;
	private Background bg;
	private float rCube, rShip, rEarth;
	
	public BasicFrameTest() throws LWJGLException{
		super();
	}
	
	public void render(){
		bg.render(getCamera().getPosition());
		glPushMatrix();
			glTranslatef(0,0,-5);
			glRotatef(rCube, 1, 1, 1);
			cube.render();
		glPopMatrix();
		glPushMatrix();
			glTranslatef(10, 0, -5);
			glRotatef(rShip, 0, 1, 0);
			glScalef(0.01f, 0.01f, 0.01f);
			ship.render();
		glPopMatrix();
		glPushMatrix();
			glRotatef(rEarth, 0, 1, 0);
			glTranslatef(30, 0, 0);
			earth.render();
		glPopMatrix();
		glPushMatrix();
			glTranslatef(7, 6, 2);
			sun.render();
		glPopMatrix();
		rCube += 0.5;
		rShip += 0.3;
		rEarth += 1;
	}
	
	public void renderInterface(){
		
		int s = 1;
		glColor3f(1,0,0);
		glTranslatef(-1.5f,0.0f,-1f);
		glBegin(GL_QUADS);
			glVertex2f(0,0);
			glVertex2f(s,0);
			glVertex2f(s,s);
			glVertex2f(0,s);
		glEnd();
	}
	
	public void init(){
		//Light.setModelAmbient(1,1,1);
		//camera.setLight(true);
		getCamera().setPosition(new Vector3(-10, 0, 0));
		getCamera().setSight(new Vector3(1,0,0));
		
		cube = ModelLoader.loader.getModel("cube.obj", ModelLoader.SHADING_FLAT);
		ship = ModelLoader.loader.getModel("Fighter.obj", ModelLoader.SHADING_PHONG);
		earth = ModelLoader.loader.getModel("earth.obj", ModelLoader.SHADING_SHPERE);
		sun = ModelLoader.loader.getModel("sun.obj");
		bg = new Background("texture__space_by_Mjag.jpg", 1000);

	}

}
