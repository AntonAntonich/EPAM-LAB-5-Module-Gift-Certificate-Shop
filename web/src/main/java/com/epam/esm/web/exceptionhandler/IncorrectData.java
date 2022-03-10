package com.epam.esm.web.exceptionhandler;

public class IncorrectData {
    private String message;

    public IncorrectData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
