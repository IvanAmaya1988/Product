package com.cda.Product.exception;

public class ResourceNotFoundException extends RuntimeException{
    private int status;

    public ResourceNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
