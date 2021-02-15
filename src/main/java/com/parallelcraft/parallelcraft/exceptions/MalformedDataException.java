package com.parallelcraft.parallelcraft.exceptions;

/**
 * Thrown when something does not comply to to the protocol standard
 * 
 * @author extremeCrazyCoder
 */
public class MalformedDataException extends RuntimeException {
    public MalformedDataException(String cause) {
        super(cause);
    }
}
