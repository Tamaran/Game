package graphics;

import mymath.depCube;

/**
 * Interface representing things that can be drawn
 * 
 * @author Tamaran
 *
 */
public interface Renderable {
	
	/**
	 * Renders the object at (0,0,0)
	 */
	public void render();
	/**
	 * @return the area where the Object is drawn if render() is called
	 */
	public depCube getBounds();

}
