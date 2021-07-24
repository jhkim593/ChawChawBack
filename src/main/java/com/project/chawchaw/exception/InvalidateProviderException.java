package com.project.chawchaw.exception;

public class InvalidateProviderException extends RuntimeException {
    public InvalidateProviderException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidateProviderException(String msg) {
        super(msg);
    }

    public InvalidateProviderException() {
        super();
    }
}