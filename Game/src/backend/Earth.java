package backend;

import graphics.Model;
import mymath.depCube;
import mymath.Vector3;
import graphics.ModelLoader;
import graphics.Renderable;



public class Earth extends Model{
	
	private static final float ROTSPEED = 0.001f;

	public Earth() {
		super(ModelLoader.loader.getModel("Earth.obj", ModelLoader.SHADING_SHPERE));
	}

	@Override
	public float getScale() {
		return 10;
	}
	
	public boolean collisionWith(Entity o){
		return true;
	}
	
	public void die(){
		game.addAnimation(new Explosion(new depCube(bounds)));
		game.setEarth(null);
	}

}
