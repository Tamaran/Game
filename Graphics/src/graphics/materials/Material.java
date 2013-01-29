package graphics.materials;

import static org.lwjgl.opengl.GL11.*;


import graphics.GLColor;
import graphics.textures.Texture;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;


public class Material {
	
	public static final Material WHITE = getWhite();
	
	private static FloatBuffer C_WHITE = GLColor.createColor(1,1,1,1),
							   C_BLACK = GLColor.createColor(0,0,0,1);
	
	private FloatBuffer ka,		//ambient
						kd,		//diffuse
						ks,		//specular
						em;		//emissive
	private float Ns, trans;
	private List<Integer> illum = new ArrayList();
	private Texture map_Ka, 
				    map_Kd, 
				    map_Ks, 
				    map_Ns;

	public Material(FloatBuffer ka, FloatBuffer kd, FloatBuffer ks,
			FloatBuffer em, Texture map_Kd) {
		super();
		this.ka = ka;
		this.kd = kd;
		this.ks = ks;
		this.em = em;
		this.map_Kd = map_Kd;
	}
	
	public Material(){}

	/**
	 * Sets this Material as current
	 */
	public void apply(){

		setMaterialParameter(GL_AMBIENT, ka);
		setMaterialParameter(GL_DIFFUSE, kd);
		setMaterialParameter(GL_SPECULAR, ks);
		setMaterialParameter(GL_EMISSION, em);
		setMaterialf(GL_SHININESS, Ns);		
		if(map_Ka != null)
			map_Ka.apply();
		if(map_Kd != null)
			map_Kd.apply();
	}
	
	public void setAmbient(float r, float g, float b){
		ka = GLColor.createColor(r, g, b, 1);
	}
	
	public void setAmbient(FloatBuffer b){
		ka = b;
	}
	
	public void setDiffuse(float r, float g, float b){
		kd = GLColor.createColor(r, g, b, 1);
	}
	
	public void setDiffuse(FloatBuffer b){
		kd = b;
	}
	
	public void setSpecular(float r, float g, float b){
		ks = GLColor.createColor(r, g, b, 1);
	}
	
	public void setSpecular(FloatBuffer b){
		ks = b;
	}
	
	public void setEmissive(float r, float g, float b){
		em = GLColor.createColor(r, g, b, 1);
	}
	
	public void setEmissive(FloatBuffer b){
		em = b;
	}
	
	public void setSpecularWeight(float w){
		Ns = w;
	}
	
	public void setTransparency(float t){
		trans = t;
	}

	public void addIllumMode(int i) {
		illum.add(i);
	}

	public Texture getMap_Ka() {
		return map_Ka;
	}

	public void setMap_Ka(Texture map_Ka) {
		this.map_Ka = map_Ka;
	}

	public Texture getMap_Kd() {
		return map_Kd;
	}

	public void setMap_Kd(Texture map_Kd) {
		this.map_Kd = map_Kd;
	}

	public Texture getMap_Ks() {
		return map_Ks;
	}

	public void setMap_Ks(Texture map_Ks) {
		this.map_Ks = map_Ks;
	}

	public Texture getMap_Ns() {
		return map_Ns;
	}

	public void setMap_Ns(Texture map_Ns) {
		this.map_Ns = map_Ns;
	}
	
	private static void setMaterialParameter(int p, FloatBuffer c){
		glColorMaterial(GL_FRONT, p);
		if(c != null)
			glColor3f(c.get(0), c.get(1), c.get(2));
		else 
			glColor3f(0,0,0);
	}
	
	private static void setMaterialf(int p, float f){
		glMaterialf(GL_FRONT, p, f);
	}
	
	private static Material getWhite(){
		Material m = new Material();
		m.setAmbient(1, 1, 1);
		return m;
	}
}
