package graphics;

import graphics.materials.Material;

import org.lwjgl.util.glu.Sphere;

/**
 * A Basic Sphere Enitity class
 * 
 * @author Tamaran
 *
 */
public class SphereModel implements Renderable{

	private Material m;
	private Sphere s = new Sphere();
	private int slices, stacks;

	/**
	 * Contructs a Sphere with the specified Material and subdivisions. The radius is 1
	 * 
	 * @param m
	 * @param slices
	 * @param stacks
	 */
	public SphereModel(Material m, int slices, int stacks) {
		super();
		this.m = m;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Applys the Material and renders the Sphere
	 */
	public void render() {
		m.apply();
		s.draw(1, slices, stacks);
	}

}
