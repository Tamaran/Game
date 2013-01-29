package test;

import java.io.IOException;

import mymath.depCube;

import backend.Explosion;


import graphics.Animation;
import graphics.BasicFrame;
import graphics.textures.Texture;
import graphics.textures.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class AnimationTest extends BasicFrame{
	
	private Explosion a;

	public void render(){
		if(a == null)
			a = new Explosion(new depCube(5,5,5,10,10,10));
		if(!a.render(getCamera().getPosition()))
			a = null;
	}

}
