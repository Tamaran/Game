package backend;

import java.util.LinkedList;
import java.util.List;

import mymath.Vector3;

public class AstroidAdder {
	
	private static float DISTANCE = 400f, SPEED = 0.3f;
	private static final int INITDELAY = 3000;
	
	private Game game;
	private long lastCreation, delay = INITDELAY;
	
	public AstroidAdder(Game game) {
		super();
		this.game = game;
	}

	public void update(){
		
		long cur = System.currentTimeMillis();
		if(cur-delay < lastCreation)
			return;
		
		Entity e = game.getEarth();
		if(e == null)
			e = game.getPlayer();
		if(e == null)
			return;
		//TODO fliegen ned auf die erde zu
		Vector3 p = new Vector3(e.getBounds().getPosition()).add(Vector3.random().mult(DISTANCE));
		Astroid n = new Astroid();
		n.setPosition(p);
		n.setDir(new Vector3(e.getBounds().getPosition()).sub(p).norm().inverse());
		n.setSpeed(SPEED);
		game.addEntity(n);
		lastCreation = cur;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	
}
