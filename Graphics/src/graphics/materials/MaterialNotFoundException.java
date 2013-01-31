/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.materials;

/**
 *
 * @author Tamaran
 */
public class MaterialNotFoundException extends Exception {

    /**
     * Creates a new instance of
     * <code>MaterialNotFoundException</code> without detail message.
     */
    public MaterialNotFoundException() {
    }

    /**
     * Constructs an instance of
     * <code>MaterialNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MaterialNotFoundException(String msg) {
        super(msg);
    }
}
