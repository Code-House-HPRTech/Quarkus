package com.hprtech.exception;

public class TechnicalException extends Exception {

    int status;

    public TechnicalException() {
    }

    public TechnicalException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
