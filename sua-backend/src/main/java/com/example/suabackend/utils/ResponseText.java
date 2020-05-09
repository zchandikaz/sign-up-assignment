package com.example.suabackend.utils;

public class ResponseText {
    private String text;
    private boolean success;

    public ResponseText(String text, boolean success) {
        this.text = text;
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
