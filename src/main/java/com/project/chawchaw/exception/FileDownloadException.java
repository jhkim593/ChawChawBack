package com.project.chawchaw.exception;

public class FileDownloadException extends RuntimeException{
    public FileDownloadException(String msg, Throwable t) {
        super(msg, t);
    }

    public FileDownloadException(String msg) {
        super(msg);
    }

    public FileDownloadException() {
        super();
    }
}
