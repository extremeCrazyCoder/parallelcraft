package com.parallelcraft.util;

/**
 * Stores a name value Pair
 * can have a Signature
 *
 * @author extremeCrazyCoder
 */
public class Property {
    private String name;
    private String value;
    private String signature;

    public Property(String name, String value) {
        this(name, value, null);
    }

    public Property(String name, String value, String signature) {
        this.name = name;
        this.value = value;
        this.signature = signature;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }

    public boolean hasSignature() {
        return signature == null;
    }
    
    @Override
    public String toString() {
        return "Property["
                + "name='" + name + "', "
                + "value='" + value + "', "
                + "signature='" + signature + "']";
    }
}
