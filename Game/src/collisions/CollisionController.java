package collisions;

import java.util.List;

import backend.EntityContainer;

public class CollisionController {
	
	private EntityContainer container;
	
	public CollisionController(EntityContainer container) {
		super();
		this.container = container;
	}

	public void checkForCollisions(){
		
		List<ColPair> col = container.getPossibleCollisions();
		int s = col.size();
		for(int i = 0; i < s; i++){
			ColPair p = col.get(i);
			CircleCollision c1 = new CircleCollision();
			if(c1.collided(p.e1, p.e2))
				if(p.e1.collisionWith(p.e2))
					p.e1.die();
		}
	}

}
