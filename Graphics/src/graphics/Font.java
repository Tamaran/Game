package graphics;

import static org.lwjgl.opengl.GL11.*;

import graphics.textures.Texture;

import java.util.Arrays;


public class Font {
	
	private String s = "";
	/**
	 * The Texture has to be ordered in a 16x16 Matrix
	 */
	private Texture tex;
	private float w, h, lw, lh;

	/**
	 * Creates a new Font.
	 * 
	 * @param tex a Texture containing all Letters of the Alphabet ordered as 16x16.
	 * @param lw the letter width
	 * @param lh the letter height
	 * @param w the width of the Quad
	 * @param h the height of the Quad
	 */
	public Font(Texture tex, float w, float h, float lw, float lh) {
		super();
		this.tex = tex;
		this.lw = lw;
		this.lh = lh;
		this.w = w;
		this.h = h;
	}

	public void setText(String s) {
		this.s = s;
	}

	public void render(){
		char[] data = s.toCharArray();
		float x = 0, y = 0;		//the letter position
		tex.apply();
		
		glBegin(GL_QUADS);
			for(char c : data){
				if(isReturn(c) || x >= w){
					x = 0;
					y -= lh;
					if(y >= h)	//If the letter is not on the Quad anymore
						break;
				}
				float[] bounds = getLetterBounds(c);
				glTexCoord2f(bounds[0],bounds[1]);
				glVertex2f(x,y);
				glTexCoord2f(bounds[2], bounds[1]);
				glVertex2f(x+lw, y);
				glTexCoord2f(bounds[2], bounds[3]);
				glVertex2f(x+lw, y+lh);
				glTexCoord2f(bounds[0], bounds[3]);
				glVertex2f(x, y+lh);
				x += lw;
			}
		glEnd();
	}
	
	
	
	public String getText() {
		return s;
	}

	public float getWidth() {
		return w;
	}

	public void setWidth(float w) {
		this.w = w;
	}

	public float getHeight() {
		return h;
	}

	public void setHeight(float h) {
		this.h = h;
	}

	public float getLetterWidth() {
		return lw;
	}

	public void setLetterWidth(float lw) {
		this.lw = lw;
	}

	public float getLetterHeight() {
		return lh;
	}

	public void setLetterHeight(float lh) {
		this.lh = lh;
	}

	/**
	 * 0 = x1, 1 = y1, 2 = x2, 3 = y2
	 */
	private float[] getLetterBounds(char c){
		
		int y = c/16;
		int x = c%16;
		
		float ls = 1f/16;

		return new float[]{x*ls, (y+1)*ls, (x+1)*ls, y*ls};
	}
	
	private boolean isReturn(char c){
		switch(c){
		case 10:
		case 12:
		case 13:
			return true;
		default: 
			return false;
		}
	}
}
