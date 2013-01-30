package graphicstest;

import java.io.IOException;


import graphics.Font;
import graphics.gui.BasicFrame;
import graphics.textures.TextureLoader;
import static org.lwjgl.opengl.GL11.*;
import util.ResLoader;

public class FontTest extends BasicFrame{

	private Font f;
	
	public void init(){
		f = new Font(TextureLoader.loader.getTexture(ResLoader.getFile("font1.png")), 199, 199, 0.3f, 0.3f);
		f.setText(" Dies ist ein Testsatz\nund noch ein Testsatz");
	}
	
	public void render(){
		glTranslatef(-1.5f,0.0f,-6.0f);
		f.render();
	}
}
