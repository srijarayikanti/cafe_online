package com.example.cafe_online.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final String errorCode;
    private final String resourceName;

    public ResourceNotFoundException(String resourceName, String message) {
        super(message);
        this.errorCode = "RESOURCE_NOT_FOUND";
        this.resourceName = resourceName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getResourceName() {
        return resourceName;
    }
}
