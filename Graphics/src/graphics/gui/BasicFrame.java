package graphics.gui;

import graphics.Camera;
import graphics.GLUtility;
import graphics.textures.Texture;

import java.awt.Toolkit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;


import static org.lwjgl.opengl.GL11.*;

/**
 * A GUI class that provides the basic functions that are necessary for rendering scenes. 
 * 
 * The class contains
 * - a camera
 * - a mainLoop 
 * - an overlay function
 * 
 * The functions that can be overwritten are
 * render(): Overwrite this to render your scene
 * renderInterface(): Overwrite this to render a interface
 * update(float): Overwrite this to call frequent updates (once per frame)
 * keydown(char): Overwrite this to react to keyboard input
 * keyhold(char): Overwrite this to react to keyboard input
 * init(): Overwrite this function to set basic Properties
 * 
 * @author Tamaran
 *
 */
public class BasicFrame {
	
	private Camera camera = new Camera();
	private Texture overlay;
	private int fps = 60;
	private int w, h;
	private boolean stopped, leftDown, rightDown;
	
	//contains the keys that are hold down
	private List<Integer> hold = new LinkedList<>();
	
	/**
	 * Constructs a new Display in windowed mode.
	 * 
	 * @param w The window width
	 * @param h The window height
	 */
	public BasicFrame(int w, int h){
		this(w,h,new Camera());
	}
	
	/**
	 * Contruckts a new Display in windowed mode with the given Camera.
	 * 
	 * @param w The window width
	 * @param h The window height
	 * @param camera a non null camera
	 */
	public BasicFrame(int w, int h, Camera camera){
		this.w = w;
		this.h = h;
		this.camera = camera;
		Mouse.setGrabbed(true);
	}
	
	/**
	 * Constructs a new Display in Fullscreen mode.
	 */
	public BasicFrame(){
		this(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
	}

	
	/**
	 * Overwrite this to render your scene. The Buffers are reset and the Camera View is set before this method is called.
	 */
	public void render(){
		
	}
	
	/**
	 * Overwrite this function to call frequent update(once per frame).
	 * Controls the camera via Mouse movement by default.
	 * 
	 * @param time the elapsed time since the last frame
	 */
	public void update(float time){
		int dx = Mouse.getDX();
		int dy = Mouse.getDY();
		
		if(dx < 0)
			camera.rotateLeft();
		else if(dx > 0)
			camera.rotateRight();
		if(dy < 0)
			camera.rotateDown();
		else if(dy > 0)
			camera.rotateUp();
	}
	
	/**
	 * Overwrite this function to react to keyboard input.
	 * Does nothing per default.
	 * 
	 * @param c the pressed character
	 */
	public void keydown(int c){
		
	}
	
	/**
	 * Is invoked if a key is hold down multiple frames.
	 * controls the camera via wasd keys per default.
	 * esc stops the application
	 * 
	 * @param c the pressed character
	 */
	public void keyhold(int c){
		switch(c){
		case Keyboard.KEY_A:
			camera.moveLeft();
			break;
		case Keyboard.KEY_D:
			camera.moveRight();
			break;
		case Keyboard.KEY_W:
			camera.moveForward();
			break;
		case Keyboard.KEY_S:
			camera.moveBack();
			break;
		case Keyboard.KEY_ESCAPE:
			System.exit(0);
			break;
		}
	}
	
	/**
	 * This method is invoked if the left mouse button is down.
	 * Switches on light per default
	 */
	public void leftMouseDown(){
		camera.getLight().enable();
	}
	
	/**
	 * This method is invoked if the right mouse button is down.
	 * Switches off light per default
	 */
	public void rightMouseDown(){
		camera.getLight().disable();
	}
	
	/**
	 * Shows an overlay instead of the scene.
	 * 
	 * call showOverlay(null) to remove the existing overlay.
	 * 
	 * @param t
	 */
	public void showOverlay(Texture t){
		overlay = t;
	}
	
	/**
	 * Sets the update Frequency
	 * 
	 * @param fps
	 */
	public void setFps(int fps){
		this.fps = fps;
	}
	
	/**
	 * This method is called before the main Loop is started.
	 */
	public void init(){
		
	}
	
	/**
	 * Invoked each Frame the left Mouse Button is hold down
	 */
	public void leftMouseHold(){
		
	}
	
	/**
	 * Invoked each Frame the right Mouse Button is hold down
	 */
	public void rightMouseHold(){
		
	}
	
	public void loop() throws LWJGLException{

		init();
		long lastUpdate = System.currentTimeMillis(), timeElap;
		while(!Display.isCloseRequested()){
			try{
				Display.sync(fps);
				//If the window is minimized
				if(!Display.isVisible() || stopped){
					lastUpdate = System.currentTimeMillis();
					continue;
				}
				//resize
				if(Display.wasResized())
					GLUtility.resize();
				//Process keyboard inputs
				while(Keyboard.next())
					keyStateChanged(Keyboard.getEventKey());
				for(int c : hold)
					keyhold(c);
				
				//Process Mouse inputs
				while(Mouse.next())
					mouseStateChanged(Mouse.getEventButton());
				if(leftDown)
					leftMouseHold();
				if(rightDown)
					rightMouseHold();

				//Update logic
				timeElap = System.currentTimeMillis()-lastUpdate;
				update(timeElap);
				lastUpdate = System.currentTimeMillis();
				
				//Overlay
				if(overlay != null)
					renderOverlay();
				else
					internRender();
				Display.update();
				
			}catch(Throwable t){
				t.printStackTrace();
			}
		}

	}
	
	/**
	 * Everything thats rendered in this method is not affected by the camera view. This is used for elements 
	 * that are moving with the camera. This method is called with lighting off.
	 */
	public void renderInterface(){
		
	}
	
	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	private void renderOverlay(){
		glLoadIdentity();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0,0);
			glTexCoord2f(0, overlay.getH());
			glVertex2f(0, h);
			glTexCoord2f(overlay.getW(), overlay.getH());
			glVertex2f(w, h);
			glTexCoord2f(overlay.getW(), 0);
			glVertex2f(w, 0);
		glEnd();
	}
	
	private void internRender(){
		GLUtility.resetBuffers();
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_LIGHTING);
		renderInterface();
		glEnable(GL_LIGHTING);
		glLoadIdentity();
		camera.setView();
		render();
	}
	
	private void keyStateChanged(int c){
		Integer i = c;	//explicit cast
		if(Keyboard.getEventKeyState()){
			keydown(c);
			hold.add(i);
		}else
			hold.remove(i);
	}
	
	private void mouseStateChanged(int b){
		
		switch(b){
		case 0:
			if(Mouse.getEventButtonState()){
				leftMouseDown();
				leftDown = true;
			}else
				leftDown = false;
			break;
		case 1: 
			if(Mouse.getEventButtonState()){
				rightMouseDown();
				rightDown = true;
			}else
				rightDown = false;
			break;
		}
	}
}
