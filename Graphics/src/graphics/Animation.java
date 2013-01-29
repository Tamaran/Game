package graphics;

import static org.lwjgl.opengl.GL11.*;
import mymath.Vector3;
import graphics.textures.Texture;

public class Animation {

	private Billboard board;
	private int frame, format;
	private float w,h;

	/**
	 * Creates an Animation from the given Texture with the given Images per row/col.
	 * 
	 * @param t
	 * @param format
	 */
	public Animation(Texture t, int format, float w, float h){
		this.w = w;
		this.h = h;
		this.format = format;
		board = new Billboard(w, h);
		board.setTexture(t);
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	/**
	 * Renders the actual frame and increments the frame counter.
	 * 
	 * @return if the animation has not finished
	 */
	public boolean render(Vector3 to){
		float picW = 1f/format;
		float picH = picW;	//bc its quadratic
		int x = frame%format;
		int y = frame/format;
		float x1 = picW*x;
		float y1 = picH*y;
		float x2 = x1 + picW;
		float y2 = y1 + picH;

		board.setTo(to);
		board.setTextureCoords(x1, y1, x2, y2);
		board.render();
		
		if(frame == format*format)
			return false;
		frame++;
		return true;
	}
	
	public void setPosition(Vector3 p){
		board.setPostion(p);
	}
}
