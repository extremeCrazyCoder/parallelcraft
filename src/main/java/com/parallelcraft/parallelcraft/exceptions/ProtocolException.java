package com.parallelcraft.parallelcraft.exceptions;

/**
 * Thrown when we get data that we don't expect / that is not allowed
 * 
 * @author extremeCrazyCoder
 */
public class ProtocolException extends RuntimeException {
    public ProtocolException(String message) {
        super(message);
    }
}
