package mymath;

import java.util.Arrays;
import java.util.Random;

/**
 * A Basic Vector class
 * 
 * @author Tamaran
 *
 */
public class Vector3 {
	
	private static final Vector3 DEFAULT = new Vector3(1,0,0);
	private static final float HALFPI = (float) Math.PI/2, PI2 = (float) (2*Math.PI);
	
	private float x,y,z;

	public Vector3() {
		super();
	}

	/**
	 * Creates a position Vector from the given coodinates
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 o){
		this(o.x, o.y, o.z);
	}
	
	public Vector3 move(float dx, float dy, float dz){
		x += dx;
		y += dy;
		z += dz;
		return this;
	}
	
	public void addX(float f){
		x += f;
	}
	
	public void addY(float f){
		y += f;
	}
	
	public void addZ(float f){
		z += f;
	}
	
	public void set(Vector3 o){
		x = o.x;
		y = o.y;
		z = o.z;
	}

	public Vector3 rotateX(float r){
		double sinr = Math.sin(r);
		double cosr = Math.cos(r);
		float ty = y;
		float tz = z;
		y = (float) (cosr*ty-sinr*tz);
		z = (float) (sinr*ty+cosr*tz);
		return this;
	}
	
	public Vector3 rotateY(float r){
		double sinr = Math.sin(r);
		double cosr = Math.cos(r);
		float tx = x;
		float tz = z;
		x = (float) (cosr*tx+sinr*tz);
		z = (float) (cosr*tz-sinr*tx);
		return this;
	}
	
	public Vector3 rotateZ(float r){
		double sinr = Math.sin(r);
		double cosr = Math.cos(r);
		float tx = x;
		float ty = y;
		x = (float) (cosr*tx-sinr*ty);
		y = (float) (sinr*tx+cosr*ty);
		return this;
	}
	
	public Vector3 rotateX90(){
		float tmp = y;
		y = -z;
		z = tmp;
		return this;
	}
	
	public Vector3 rotateX270(){
		float tmp = y;
		y = z;
		z = -tmp;
		return this;
	}
	
	public Vector3 rotateY90(){
		float tmp = x;
		x = z;
		z = -tmp;
		return this;
	}
	
	public Vector3 rotateY270(){
		float tmp = x;
		x = -z;
		z = tmp;
		return this;
	}
	
	public Vector3 rotateZ90(){
		float tmp = x;
		x = -y;
		y = tmp;
		return this;
	}
	
	public Vector3 rotateZ270(){
		float tmp = x;
		x = y;
		y = -tmp;
		return this;
	}
	
	/**
	 * Rotates around the bodys Z-Axis.
	 * 
	 * @param r
	 */
	public Vector3 rotateBodyZ(float r){//TODO testen
		rotate(r, getBodyAxisZ());
		return this;
	}
	
	/**
	 * Rotates around the bodys Y-Axis.
	 * 
	 * @param r
	 */
	public Vector3 rotateBodyY(float r){
		rotate(r, getBodyAxisY());
		return this;
	}
	
	/**
	 * Rotates the vector by the given angle around the given axis.
	 * 
	 * @param rv
	 * @param axis
	 */
	public void rotate(float t, Vector3 u) {

		float cost = (float) Math.cos(t);
		float sint = (float) Math.sin(t);
		
		float tx = x * (cost+u.x*u.x*(1-cost))+
				   y * (u.x*u.y*(1-cost)-u.z*sint)+
				   z * (u.x*u.z*(1-cost)+u.y*sint);
		float ty = x * (u.x*u.y*(1-cost)+u.z*sint)+
				   y * (cost+u.y*u.y*(1-cost))+
				   z * (u.y*u.z*(1-cost)-u.x*sint);
		float tz = x * (u.x*u.z*(1-cost)-u.y*sint)+
				   y * (u.y*u.z*(1-cost)+u.x*sint)+
				   z * (cost+u.z*u.z*(1-cost));
 		
		x = tx;
		y = ty;
		z = tz;
	}
	
	public Vector3 add(Vector3 o){
		x += o.x;
		y += o.y;
		z += o.z;
		return this;
	}
	
	public Vector3 sub(Vector3 o){
		x -= o.x;
		y -= o.y;
		z -= o.z;
		return this;
	}
	
	/**
	 * multiplies the vector with the given number, increasing its length
	 * 
	 * @param s
	 * @return itself
	 */
	public Vector3 mult(float s){
		x *= s;
		y *= s;
		z *= s;
		return this;
	}
	
	/**
	 * normalizes the vector. after call the length is 1. The direction remains unchanged
	 * 
	 * @return itself
	 */
	public Vector3 norm(){
		float l = length();
		x /= l;
		y /= l;
		z /= l;
		return this;
	}
	
	public float length(){
		return (float) Math.sqrt(x*x+y*y+z*z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * inverses the direction of the vector. 
	 * Does the same as mult(-1f) but this one is faster.
	 * 
	 * @return itself
	 */
	public Vector3 inverse() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	@Override
	public String toString() {
		return "Vector3 [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public Vector3 cross(Vector3 b) {
		float tx = y * b.z - z * b.y;
		float ty = z * b.x - x * b.z;
		float tz = x * b.y - y * b.x;
		x = tx;
		y = ty;
		z = tz;
		return this;
	}
	
	public float dot(Vector3 b){
		return x*b.x + y*b.y + z*b.z;
	}

	/**
	 * Calculates the Axis for the axis-angle representation.
	 * Relative to the (1,0,0) vector.
	 * 
	 * @return
	 */
	public Vector3 getAxis(){
		Vector3 a = new Vector3(this);
		a.cross(DEFAULT);
		return a;
	}

	/**
	 * Calculates the Angle for the axis-angle representation
	 * Relative to the (1,0,0) vector.
	 * 
	 * @return
	 */
	public float getAngle() {
		Vector3 a = new Vector3(this);
		a.norm();
		return (float) Math.acos(a.x);
		
	}
	
	/**
	 * Computes the rotation around the y and the z axis to transform a (1,0,0) vector into this vector.
	 * 
	 * @return [ry,rz]
	 */
	public float[] asYZRotation(){//TODO macht fehler
		float[] res = new float[2];
		Vector3 xzproj = new Vector3(x, 0, z);
		xzproj.norm();
		res[0] = (float) Math.acos(xzproj.x);
		Vector3 a = new Vector3(this);
		a.norm();
		res[1] = (float) Math.asin(a.y);
		if(Float.isNaN(res[0]))
			res[0] = 0f;
		if(Float.isNaN(res[1]))
			res[1] = 0f;
		if(z < 0)
			res[0] = Constants.TWOPI - res[0];
		return res;
	}
	
	/**
	 * @return the vector itself.
	 */
	public Vector3 getBodyAxisX(){
		return this;
	}
	
	/**
	 * @return the Bodys Y Axis.
	 */
	public Vector3 getBodyAxisY(){
		Vector3 z = getBodyAxisZ();
		z.cross(this);
		return z;
	}
	
	/**
	 * The Body Y axis.
	 * 
	 * @return
	 */
	public Vector3 getBodyAxisZ(){
		
		Vector3 y = new Vector3(0,1,0);
		y.cross(this);
		return y;
	}
	
	/**
	 * Construcs a direction vector from an object that started with the (1,0,0) vector and then got rotated by the 
	 * given body coordinates.
	 * 
	 * @param rv
	 * @param rh
	 * @return
	 */
	public static Vector3 fromRotations(float rv, float rh){
		Vector3 v = new Vector3(1,0,0);
		v.rotateY(rh);
		Vector3 axis = new Vector3(v).rotateY90();
		v.rotate(rv, axis);
		return v;
	}
	
	/**
	 * Calculates the distance between 2 position vectors.
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static float getDistance(Vector3 v1, Vector3 v2){
		float x = v1.x-v2.x;
		float y = v1.y-v2.y;
		float z = v1.z-v2.z;
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
	
	public static Vector3 random(){
		Random rnd = new Random();
		Vector3 v = new Vector3();
		v.x = (float) (Math.random()*2-1);
		v.y = (float) (Math.random()*2-1);
		v.z = (float) (Math.random()*2-1);
		v.norm();
		return v;
	}
	
	private void saveNorm(){
		float d = 1f/(length()*1.01f);
		x /= d;
		y /= d;
		z /= d;
	}
}
