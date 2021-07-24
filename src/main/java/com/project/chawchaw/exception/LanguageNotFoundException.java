package com.project.chawchaw.exception;

public class LanguageNotFoundException extends RuntimeException{

    public LanguageNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    public LanguageNotFoundException(String msg) {
        super(msg);
    }
    public LanguageNotFoundException() {
        super();
    }
}
