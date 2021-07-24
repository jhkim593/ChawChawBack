package com.project.chawchaw.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException() {
        super();
    }
}