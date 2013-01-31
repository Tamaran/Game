/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Tamaran
 */
public class LoadException extends Exception {

    /**
     * Creates a new instance of
     * <code>LoadException</code> without detail message.
     */
    public LoadException() {
    }

    /**
     * Constructs an instance of
     * <code>LoadException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoadException(String msg) {
        super(msg);
    }

    public LoadException(Throwable cause) {
        super(cause);
    }

    public LoadException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
