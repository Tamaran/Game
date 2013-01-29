package collisions;

import mymath.depCube;
import mymath.Vector3;
import backend.Entity;

public class CircleCollision implements CollisionChecker {

	@Override
	public boolean collided(Entity e1, Entity e2) {

		depCube s1 = e1.getBounds();
		depCube s2 = e2.getBounds();
		float r1 = Math.max(Math.max(s1.getWidth(), s1.getHeight()), s1.getDepth())/2;
		float r2 = Math.max(Math.max(s2.getWidth(), s2.getHeight()), s2.getDepth())/2;
		float dist = Vector3.getDistance(e1.getBounds().getPosition(), e2.getBounds().getPosition());
		if(dist > r1+r2)
			return false;
		return true;
	}

}
