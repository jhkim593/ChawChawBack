package com.project.chawchaw.exception;

public class ChatRoomNotFoundException extends RuntimeException {
    public ChatRoomNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ChatRoomNotFoundException(String msg) {
        super(msg);
    }

    public ChatRoomNotFoundException() {
        super();
    }
}