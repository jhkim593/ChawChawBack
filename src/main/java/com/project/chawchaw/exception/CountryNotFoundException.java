package com.project.chawchaw.exception;

public class CountryNotFoundException extends  RuntimeException{
    public CountryNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    public CountryNotFoundException(String msg) {
        super(msg);
    }
    public CountryNotFoundException() {
        super();
    }
}
