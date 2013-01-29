package backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import collisions.ColPair;

public class EntityContainer implements Iterable<Entity>{
	
	private Queue<Entity> entitys = getList();
	
	public void add(Entity e){
		entitys.add(e);
	}
	
	public void remove(Entity e){
		entitys.remove(e);
	}

	public Iterator<Entity> iterator() {
		return entitys.iterator();
	}

	public List<ColPair> getPossibleCollisions() {
		List<ColPair> list = new LinkedList();
		int s = entitys.size();
		int i = 0;
		int j = 0;
		for(Entity a : entitys)
			for(Entity b : entitys)
				if(a != b)
					list.add(new ColPair(a, b));
		return list;
	}
	
	public String toString(){
		return entitys.toString();
	}
	
	private Queue getList(){
		return new ConcurrentLinkedQueue();
	}

}
