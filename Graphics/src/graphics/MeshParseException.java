/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

/**
 *
 * @author Tamaran
 */
public class MeshParseException extends Exception {

    /**
     * Creates a new instance of
     * <code>ModelParseException</code> without detail message.
     */
    public MeshParseException() {
    }

    /**
     * Constructs an instance of
     * <code>ModelParseException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MeshParseException(String msg) {
        super(msg);
    }

    public MeshParseException(Throwable cause) {
        super(cause);
    }
    
    
}
