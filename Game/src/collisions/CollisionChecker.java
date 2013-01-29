package collisions;

import backend.Entity;

public interface CollisionChecker {
	
	public boolean collided(Entity e1, Entity e2);

}
