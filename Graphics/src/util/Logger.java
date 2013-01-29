/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Tamaran
 */
public class Logger {
    
    public static void D(String s)
    {
        System.out.println(s);
    }
    
    public static void E(String s)
    {
        System.err.println(s);
    }
    
    public static void E(Throwable e)
    {
        System.out.println(e);
    }
    
    public static void W(String s)
    {
        System.out.println(s);
    }
    
}
