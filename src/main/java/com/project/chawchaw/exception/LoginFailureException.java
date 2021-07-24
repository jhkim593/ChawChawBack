package com.project.chawchaw.exception;

public class LoginFailureException extends RuntimeException {
    public LoginFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginFailureException(String msg) {
        super(msg);
    }

    public LoginFailureException() {
        super();
    }
}