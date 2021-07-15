package com.parallelcraft.util;

import java.util.ArrayList;

/**
 *
 * @author extremeCrazyCoder
 */
public class AdvancedList<V> extends ArrayList<V> {
    public AdvancedList() {
        super();
    }
    
    public AdvancedList<V> append(V value) {
        super.add(value);
        return this;
    }
    
    /**
     * should be a thread save get and remove for the first element
     * @return the first element
     */
    public V fetchFirst() {
        V elm = get(0);
        remove(0);
        return elm;
    }
}
