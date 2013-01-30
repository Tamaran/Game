package graphics.face;

import static org.lwjgl.opengl.GL11.*;


import graphics.MeshLoader;
import graphics.materials.Material;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import mymath.Vector3;

/**
 * A Face of a Polygon
 * 
 * @author Tamaran
 *
 */
public class Polygon {
	
	private Material material;
	private List<Vertex> v;
	private List<TextureCoodinate> vt;
	private List<Vector3> vNorm;
	private Vector3 normal;
	private int shadingMode;
	
	public Polygon(){
		v = getList();
		vt = getList();
		vNorm = getList();
	}

	public Polygon(Polygon p) {
		
		material = p.material;
		v = copy(p.v);
		vt = copy(p.vt);
		vNorm = copy(p.vNorm);
		if(p.normal != null)
                {
                    normal = p.normal;
                }
		shadingMode = p.shadingMode;
	}

	/**
	 * Renders the Face at the current position.
	 * 
	 * Because of performance issuses you have to set the Material yourself before you call this method.
	 * face.getMaterial().apply();
	 * face.render();
	 */
	public void render() {
		
		if(shadingMode == MeshLoader.SHADING_FLAT)
			glNormal3f(normal.x, normal.y, normal.z);
		
		int l = v.size();
		glBegin(getRenderMode());
		for(int i = 0; i < l; i++){
			if(!vt.isEmpty()){
				TextureCoodinate tc = vt.get(i);
				glTexCoord2f(tc.getX(), tc.getY());
			}
			if(shadingMode != MeshLoader.SHADING_FLAT){
				Vector3 normal = vNorm.get(i);
				glNormal3f(normal.x, normal.y, normal.z);
			}
			Vertex vertex = v.get(i);
			glVertex3f(vertex.getX(), vertex.getY(), vertex.getZ());
		}
		glEnd();

	}
	
	public void addVertex(Vertex vertex){
		v.add(vertex);
	}
	
	public void addTexCood(TextureCoodinate c){
		vt.add(c);
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Vector3 getNormal() {
		return normal;
	}

	public void setNormal(Vector3 normal) {
		this.normal = normal;
	}
	
	public List<Vertex> getVertices(){
		return v;
	}
	
	public List<Vector3> getVertexNormals(){
		return vNorm;
	}

	public int getShadingMode() {
		return shadingMode;
	}

	public void setShadingMode(int shadingMode) {
		this.shadingMode = shadingMode;
	}
	
	private List getList(){
		return new ArrayList();
	}
	
	private List copy(List l){
		List c = new ArrayList(l.size());
		for(Object o : l)
			c.add(o);
		return c;
	}
	
	private int getRenderMode(){
		switch(v.size()){
		case 3:
			return GL_TRIANGLES;
		case 4:
			return GL_QUADS;
		default:
			return GL_POLYGON;
		}
	}
}
