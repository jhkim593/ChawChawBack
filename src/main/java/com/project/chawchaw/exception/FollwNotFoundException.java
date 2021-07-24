package com.project.chawchaw.exception;

public class FollwNotFoundException extends RuntimeException {
    public FollwNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    public FollwNotFoundException(String msg) {
        super(msg);
    }
    public FollwNotFoundException() {
        super();
    }
}