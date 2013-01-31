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
    
    private HashMap<K, E> map;
    
    protected abstract E read(K name);
    
    public E get(K name)
    {
        E e = map.get(name);
        if(e == null)
        {
            e = read(name);
            map.put(name, e);
        }
        return e;
    }
}
