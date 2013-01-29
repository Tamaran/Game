package backend;

import graphics.ModelLoader;
import graphics.Renderable;

public class Astroid extends Entity{

	private static final int INITSIZE = 4, BASIC_SCALE = 1;
	
	/**
	 * Die Zahl der Spaltungen die der asteroid noch machen kann und gleichzeitig die aktuelle größe
	 */
	private int size = INITSIZE;
	
	public Astroid() {
		super(ModelLoader.loader.getModel("astroid.obj", ModelLoader.SHADING_PHONG));
	}

	@Override
	public float getScale() {
		return BASIC_SCALE*size;
	}
	
	public boolean collisionWith(Entity o){
		return true;
	}
	
}
