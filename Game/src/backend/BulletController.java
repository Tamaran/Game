package backend;


import java.util.Vector;

import mymath.Vector3;

public class BulletController {

	private final long SHOTDELAY = 100;
	private final float SHOTSPEED = 5;
	
	private long lastShot;

	private Game game;
	

	public BulletController(Game game) {
		super();
		this.game = game;
	}

	/**
	 * fires a shot if its possible.
	 */
	public void fireBullet() {
		
		long akt = System.currentTimeMillis();
		if(akt-lastShot < SHOTDELAY)
			return;
		Player p = game.getPlayer();
		if(p == null)
			return;
		
		lastShot = akt;
		Bullet b = new Bullet();
		b.game = game;
		b.setPosition(p.getPosition());
		b.rh = p.rh;
		b.rv = p.rv;
		b.setSpeed(SHOTSPEED);
		game.entitys.add(b);
	}

}