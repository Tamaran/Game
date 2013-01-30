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
public class ResourceLoader {
    
    private String folder;
    
    public ResourceLoader(String folder)
    {
        this.folder = folder;
    }
    
    public File getFile(String name)
    {
        return new File(folder+name);
    }
    
}
