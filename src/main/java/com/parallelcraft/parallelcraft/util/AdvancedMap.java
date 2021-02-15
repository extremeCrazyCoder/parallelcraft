package com.parallelcraft.parallelcraft.util;

import java.util.HashMap;

/**
 *
 * @author extremeCrazyCoder
 */
public class AdvancedMap<K, V> extends HashMap<K, V> {
    public AdvancedMap() {
        super();
    }
    
    public AdvancedMap<K, V> insert(K key, V value) {
        super.put(key, value);
        return this;
    }
}
