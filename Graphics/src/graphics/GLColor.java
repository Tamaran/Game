package graphics;

import java.nio.FloatBuffer;

/**
 * Entity class that represents Color
 * 
 * @author Tamaran
 *
 */
public class GLColor {
	
	public static final FloatBuffer BLACK = readOnlyColor(0,0,0),
									WHITE = readOnlyColor(1,1,1),
									RED = readOnlyColor(1,0,0),
									GREEN = readOnlyColor(0,1,0),
									BLUE = readOnlyColor(0,0,1),
									DARKGRAY = readOnlyColor(0.2f,0.2f,0.2f),
									BRIGHTGRAY = readOnlyColor(0.6f,0.6f,0.6f),
									YELLOW = readOnlyColor(1,1,0);
	
	/**
	 * Returns a FloatBuffer that contains the specified Color
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	public static FloatBuffer createColor(float r, float g, float b, float a){
		FloatBuffer buf = GLUtility.directFloatBuffer(4);
		buf.put(r);
		buf.put(g);
		buf.put(b);
		buf.put(a);
		buf.rewind();
		return buf;
	}
	
	private static FloatBuffer readOnlyColor(float r, float g, float b){
		return createColor(r,g,b,1).asReadOnlyBuffer();
	}

}
