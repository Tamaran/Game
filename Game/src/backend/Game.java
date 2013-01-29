package backend;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import backend.constantEffects.ConstantEffect;

import collisions.ColPair;
import collisions.CollisionController;

import sound.SoundManager;


import graphics.gui.MainFrame;
import graphics.Animation;

public class Game {
	
	private static final int DIFF = 10;

	protected MainFrame frame;
	protected Player player;
	protected EntityContainer entitys;
	private AstroidAdder adder = new AstroidAdder(this);
	private List<ConstantEffect> effects = new LinkedList();
	private List<Explosion> explosions = new LinkedList();
	private CollisionController cc;
	
	private Earth earth;
	
	private boolean running;
	
	public Game(){
		entitys = new EntityContainer();
		cc = new CollisionController(entitys);
	}
	
	public void render(){
		Iterator<Entity> it = entitys.iterator();
		Iterator<Explosion> ait = explosions.iterator();
		while(it.hasNext())
			it.next().render();
		while(ait.hasNext()){
			if(!ait.next().render(frame.getCamera().getPosition()))
				ait.remove();
		}
	}
	
	public void doLogic(){
		Iterator<ConstantEffect> efit = effects.iterator();
		while(efit.hasNext())
			if(efit.next().apply())
				efit.remove();
		Iterator<Entity> it = entitys.iterator();
		while(it.hasNext())
			it.next().doLogic();
		cc.checkForCollisions();
		adder.update();
	}
	
	public MainFrame getFrame() {
		return frame;
	}
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		if(this.player != null){
			entitys.remove(this.player);
			this.player = null;
		}
		if(player != null){
			entitys.add(player);
			player.game = this;
			this.player = player;
		}
	}
	
	public void addEntity(Entity e){
		e.game = this;
		entitys.add(e);
	}
	
	public void removeEntity(Entity e) {
		entitys.remove(e);
	}

	public void lose() {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
	public void addEffect(ConstantEffect e){
		effects.add(e);
	}

	public void addAnimation(Explosion a) {
		explosions.add(a);
	}

	public Earth getEarth() {
		return earth;
	}

	public void setEarth(Earth earth) {
		if(this.earth != null){
			entitys.remove(this.earth);
			this.earth = null;
		}
		if(earth != null){
			earth.game = this;
			entitys.add(earth);
			this.earth = earth;
		}
	}

}
