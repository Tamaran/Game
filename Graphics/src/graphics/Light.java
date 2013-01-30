package graphics;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

public class Light {
	
	/*
	 * 0 is directional
	 * 1 is spot
	 */

	private static boolean[] lights = new boolean[GL_MAX_LIGHTS];
	
	private int handle;
	private FloatBuffer p;

	/**
	 * Creates a new Light at the specified position.
	 * Its disabled at creation
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Light(float x, float y, float z, boolean directional){
		handle = getFreeHandle();
		p = GLUtility.directFloatBuffer(4);
		p.put(x);
		p.put(y);
		p.put(z);
		p.put(directional?0:1);
		p.flip();
		setAmbient(GLColor.BLACK);
		setDiffuse(GLColor.WHITE);
		setSpecular(GLColor.WHITE);
	}
	
	public Light(){
		this(0,0,0,false);
	}
	
	public void enable(){
		glEnable(handle);
	}
	
	public void disable(){
		glDisable(handle);
	}
	
	public void setPosition(float x, float y, float z){
		p.put(0, x);
		p.put(1, y);
		p.put(2, z);
		glLight(handle, GL_POSITION, p);
	}
	
	public void moveToOrigin(){
		setPosition(0,0,0);
	}
	
	public void setAmbient(FloatBuffer a){
		glLight(handle, GL_AMBIENT, a);
	}
	
	public void setDiffuse(FloatBuffer d){
		glLight(handle, GL_DIFFUSE, d);
	}
	
	public void setSpecular(FloatBuffer s){
		glLight(handle, GL_SPECULAR, s);
	}
	
	public void dispose(){
		disable();
		lights[handle-GL_LIGHT0] = false;
	}
	
	public static void setModelAmbient(float r, float g, float b){
		glLightModel(GL_LIGHT_MODEL_AMBIENT , GLUtility.asFloatBuffer(r,g,b,1));
	}
	
	private static int getFreeHandle(){
		for(int i = 0; i < lights.length; i++)
			if(!lights[i]){
				lights[i] = true;
				return GL_LIGHT0+i;
			}
		throw new RuntimeException("There is a maximum of "+GL_MAX_LIGHTS+" supported");
	}
}
