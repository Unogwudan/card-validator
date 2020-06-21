package com.unogwudan.exception;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class DataEncryptionException extends Exception {
    public DataEncryptionException(String message) {
        super(message);
    }

    public DataEncryptionException(Throwable cause) {
        super(cause);
    }
}
