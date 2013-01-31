/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;

/**
 *
 * @author Tamaran
 */
public abstract class LazyLoader<K, E> {
    
    private HashMap<K, E> map = new HashMap<>();
    
    protected abstract E read(K name) throws Exception;
    
    public E get(K name) throws LoadException
    {
        E e = map.get(name);
        if(e == null)
        {
            long time = System.currentTimeMillis();
            try
            {
                e = read(name);
            }
            catch(Exception ex)
            {
                throw new LoadException(name.toString(), ex);
            }
            Logger.D(String.format("Loading %s finished after %d ms", name.toString(), System.currentTimeMillis()-time));
            map.put(name, e);
        }
        return e;
    }
}
