package backend;

import mymath.depCube;
import mymath.Vector3;
import graphics.Animation;
import graphics.textures.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class Explosion extends Animation{

	public Explosion(depCube b){
		super(TextureLoader.loader.getTexture("explosion0.png"), 16, getSize(b), getSize(b));
		this.setPosition(b.getPosition());
	}
	
	private static float getSize(depCube b){
		return Math.max(Math.max(b.getWidth(), b.getHeight()), b.getDepth());
	}
}
