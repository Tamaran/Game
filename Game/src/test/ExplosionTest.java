package test;

import org.lwjgl.opengl.GL11;

import mymath.depCube;
import backend.Explosion;
import graphics.BasicFrame;

public class ExplosionTest extends BasicFrame {
	
	private Explosion e;
	private float x = 0;

	public void render(){
		if(e == null)
			e = new Explosion(new depCube(0,0,0,10,10,10));
		if(!e.render(getCamera().getPosition()))
			e = null;
	}

}
