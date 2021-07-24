package com.project.chawchaw.exception;

public class FollowAlreadyException extends RuntimeException {
    public FollowAlreadyException(String msg, Throwable t) {
        super(msg, t);
    }

    public FollowAlreadyException(String msg) {
        super(msg);
    }

    public FollowAlreadyException() {
        super();
    }
}