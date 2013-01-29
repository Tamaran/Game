package Controller;

import graphics.gui.StickyCamera;
import graphics.gui.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import mymath.Vector3;

import backend.*;
import backend.constantEffects.ConstantRotation;
import graphics.Camera;

public class NewGameController {
	
	private Game game;
	private MainFrame mainFrame;
	private Player player;

	/**
	 * Initiates a new Empty game with a Player. 
	 */
	public void initEmptyGame(){
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		game = new Game();
		player = new Player();
		StickyCamera camera = new StickyCamera();
		mainFrame = new MainFrame(d.width,d.height,camera);
		
		camera.stick(player);
		game.setPlayer(player);
		game.setFrame(mainFrame);
		mainFrame.setGame(game);
		
	}
	
	public void initDefaultGame(){
		initEmptyGame();
		Earth earth = new Earth();
		Sun sun = new Sun();
		
		earth.setPosition(new Vector3(150, 0, 0));
		sun.setPosition(new Vector3(0, 0, -800));
		game.addEffect(new ConstantRotation(earth, 0, 0.005f));
		game.addEffect(new ConstantRotation(sun, 0, 0.0005f));
		
		game.setEarth(earth);
		game.addEntity(sun);
	}

	public Game getGame() {
		return game;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public Player getPlayer() {
		return player;
	}
	
}
