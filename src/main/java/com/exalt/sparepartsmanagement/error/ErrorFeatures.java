package com.exalt.sparepartsmanagement.error;

public class ErrorFeatures {
    private String message;
    private String uri;

    public ErrorFeatures(String message, String uri) {
        this.message = message;
        this.uri = uri;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
