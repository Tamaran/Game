package frontend;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


import graphics.Background;
import graphics.BasicFrame;
import graphics.Camera;
import graphics.GLColor;
import graphics.GLUtility;
import graphics.Light;
import graphics.textures.TextureLoader;

public class MainFrame extends BasicFrame{
	
	private static final float CAMROTSPEED = 0.000005f;
	
	private Game game;
	private Background bg;
	private BulletController bc;

	public MainFrame() {
		super();
	}

	public MainFrame(int w, int h, Camera camera) {
		super(w, h, camera);
	}

	public MainFrame(int w, int h) {
		super(w, h);
	}
	
	public void init(){
		Mouse.setGrabbed(false);
		bg = new Background("Star_Texture_Pack_by_Zephroth.jpg", GLUtility.VISION_FAR/1.4f);
		Light.setModelAmbient(1, 1, 1);
		//getCamera().getLight().enable();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
		bc = new BulletController(game);
	}
	
	public void render(){
		bg.render(getCamera().getPosition());
		game.render();
	}
	
	public void keydown(int c){
		
	}

	public void keyhold(int c){
		
		switch(c){
		case Keyboard.KEY_A:
			game.getPlayer().moveLeft();
			break;
		case Keyboard.KEY_D:
			game.getPlayer().moveRight();
			break;
		case Keyboard.KEY_W:
			game.getPlayer().speedUp();
			break;
		case Keyboard.KEY_S:
			game.getPlayer().slowDown();
			break;
		case Keyboard.KEY_ESCAPE:
			System.exit(0);
			break;
		}
		
	}
	
	public void update(float timeElap){
		
		Player p = game.getPlayer();
		if(p != null){
			DisplayMode d = Display.getDisplayMode();
			int dx = Mouse.getX()-d.getWidth()/2;
			int dy = Mouse.getY()-d.getHeight()/2;
			
			if(dx < 0)
				p.rotateLeft(-timeElap*dx*CAMROTSPEED);
			else
				p.rotateRight(timeElap*dx*CAMROTSPEED);
			if(dy < 0)
				p.rotateDown(-timeElap*dy*CAMROTSPEED);
			else
				p.rotateUp(timeElap*dy*CAMROTSPEED);
		}

		game.doLogic();		
	}
	
	public void leftMouseHold(){
		bc.fireBullet();
	}
	
	public void rightMouseHold(){
		
	}
	
	public void leftMouseDown(){
		
	}
	
	public void rightMouseDown(){
		
	}
}
