/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;

/**
 *
 * @author Tamaran
 */
public class ResLoader {
    
    public static File getFile(String name)
    {
        return new File(ResLoader.class.getResource("../res/"+name).getFile());
    }
    
}
