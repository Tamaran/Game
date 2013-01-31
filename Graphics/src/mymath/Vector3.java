package mymath;

import graphics.face.Vertex;
import java.util.Arrays;
import java.util.Random;

/**
 * A Basic Vector class
 *
 * @author Tamaran
 *
 */
public class Vector3 {

    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    private static final Vector3 DEFAULT = new Vector3(1, 0, 0);
    public final float x, y, z;

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
    
    public Vector3(Vertex v)
    {
        x = v.getX();
        y = v.getY();
        z = v.getZ();
    }

    public Vector3 add(float dx, float dy, float dz) {
        return new Vector3(x + dx, y + dy, z + dz);
    }

    public Vector3 rotateX(float r) {
        double sinr = Math.sin(r);
        double cosr = Math.cos(r);
        return new Vector3(x, (float) (cosr * y - sinr * z), (float) (sinr * y + cosr * z));
    }

    public Vector3 rotateY(float r) {
        double sinr = Math.sin(r);
        double cosr = Math.cos(r);
        return new Vector3((float) (cosr * x + sinr * z), y, (float) (cosr * z - sinr * x));
    }

    public Vector3 rotateZ(float r) {
        double sinr = Math.sin(r);
        double cosr = Math.cos(r);
        return new Vector3((float) (cosr * x - sinr * y), (float) (sinr * x + cosr * y), z);
    }

    public Vector3 rotateX90() {
        return new Vector3(x, -z, y);
    }

    public Vector3 rotateX270() {
        return new Vector3(x, z, -y);
    }

    public Vector3 rotateY90() {
        return new Vector3(z, y, -x);
    }

    public Vector3 rotateY270() {
        return new Vector3(-z, y, x);
    }

    public Vector3 rotateZ90() {
        return new Vector3(-y, x, z);
    }

    public Vector3 rotateZ270() {
        return new Vector3(y, -x, z);
    }

    /**
     * Rotates around the bodys Z-Axis.
     *
     * @param r
     */
    public Vector3 rotateBodyZ(float r) {
        return rotate(r, getBodyAxisZ());
    }

    /**
     * Rotates around the bodys Y-Axis.
     *
     * @param r
     */
    public Vector3 rotateBodyY(float r) {
        return rotate(r, getBodyAxisY());
    }

    /**
     * Rotates the vector by the given angle around the given axis.
     *
     * @param rv
     * @param axis
     */
    public Vector3 rotate(float t, Vector3 u) {

        float cost = (float) Math.cos(t);
        float sint = (float) Math.sin(t);
        return new Vector3(
                x * (cost + u.x * u.x * (1 - cost))
                + y * (u.x * u.y * (1 - cost) - u.z * sint)
                + z * (u.x * u.z * (1 - cost) + u.y * sint),
                x * (u.x * u.y * (1 - cost) + u.z * sint)
                + y * (cost + u.y * u.y * (1 - cost))
                + z * (u.y * u.z * (1 - cost) - u.x * sint),
                x * (u.x * u.z * (1 - cost) - u.y * sint)
                + y * (u.y * u.z * (1 - cost) + u.x * sint)
                + z * (cost + u.z * u.z * (1 - cost)));
    }

    public Vector3 add(Vector3 o) {
        return new Vector3(x + o.x, y + o.y, z + o.z);
    }

    public Vector3 sub(Vector3 o) {
        return new Vector3(x - o.x, y - o.y, z - o.z);
    }

    /**
     * multiplies the vector with the given number, increasing its length
     *
     * @param s
     * @return itself
     */
    public Vector3 mult(float s) {
        return new Vector3(x * s, y * s, z * s);
    }

    /**
     * normalizes the vector. after call the length is 1. The direction remains
     * unchanged
     *
     * @return itself
     */
    public Vector3 norm() {
        float l = length();
        return new Vector3(x / l, y / l, z / l);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * inverses the direction of the vector. Does the same as mult(-1f) but this
     * one is faster.
     *
     * @return itself
     */
    public Vector3 inverse() {
        return new Vector3(-x, -y, -z);
    }

    @Override
    public String toString() {
        return "Vector3 [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    public Vector3 cross(Vector3 b) {
        return new Vector3(
                y * b.z - z * b.y,
                z * b.x - x * b.z,
                x * b.y - y * b.x);
    }

    public float dot(Vector3 b) {
        return x * b.x + y * b.y + z * b.z;
    }

    /**
     * Calculates the Axis for the axis-angle representation. Relative to the
     * (1,0,0) vector.
     *
     * @return
     */
    public Vector3 getAxis() {
        return cross(DEFAULT);
    }

    /**
     * Calculates the Angle for the axis-angle representation Relative to the
     * (1,0,0) vector.
     *
     * @return
     */
    public float getAngle() {
        return (float) Math.acos(norm().x);

    }

    /**
     * @return the vector itself.
     */
    public Vector3 getBodyAxisX() {
        return this;
    }

    /**
     * @return the Bodys Y Axis.
     */
    public Vector3 getBodyAxisY() {
        return getBodyAxisZ().cross(this);
    }

    /**
     * The Body Y axis.
     *
     * @return
     */
    public Vector3 getBodyAxisZ() {
        return new Vector3(0, 1, 0).cross(this);
    }

    /**
     * Calculates the distance between 2 position vectors.
     *
     * @param v1
     * @param v2
     * @return
     */
    public static float getDistance(Vector3 v1, Vector3 v2) {
        float x = v1.x - v2.x;
        float y = v1.y - v2.y;
        float z = v1.z - v2.z;
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public static Vector3 random() {
        Vector3 v = new Vector3(
                (float) (Math.random() * 2 - 1),
                (float) (Math.random() * 2 - 1),
                (float) (Math.random() * 2 - 1));
        return v.norm();
    }
}
