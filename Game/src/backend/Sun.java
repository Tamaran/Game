package backend;

import org.lwjgl.opengl.GL11;

import mymath.Vector3;
import graphics.GLColor;
import graphics.GLUtility;
import graphics.Light;
import graphics.Model;
import graphics.ModelLoader;
import graphics.SphereModel;
import graphics.materials.Material;

public class Sun extends Entity{
	
	private Light[] lights = new Light[6];

	public Sun(){
		super(load());
		for(int i = 0; i < lights.length; i++){
			Light l = new Light();
			lights[i] = l;
			l.setSpecular(GLColor.DARKGRAY);
			l.enable();
		}
	}
	
	public void render(){
		Vector3 v = bounds.getPosition();
		float hr = bounds.getHeight()/2;
		lights[0].setPosition(v.getX()+hr, v.getY(), v.getZ());
		lights[1].setPosition(v.getX()-hr, v.getY(), v.getZ());
		lights[2].setPosition(v.getX(), v.getY()+hr, v.getZ());
		lights[3].setPosition(v.getX(), v.getY()-hr, v.getZ());
		lights[4].setPosition(v.getX(), v.getY(), v.getZ()-hr);
		lights[5].setPosition(v.getX(), v.getY(), v.getZ()+hr);
		super.render();
	}

	@Override
	public float getScale() {
		return 500;
	}
	
	private static Model load(){
		Model m = ModelLoader.loader.getModel("sun.obj");
		m.getFirstMaterial().setEmissive(1, 1, 1);
		return m;
	}

}
