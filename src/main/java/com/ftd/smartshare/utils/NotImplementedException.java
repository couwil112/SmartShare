package com.ftd.smartshare.utils;

public class NotImplementedException extends UnsupportedOperationException {
    private static final long serialVersionUID = 20131021L;
    private final String code;

    public NotImplementedException() {
        this("Not implemented");
    }

    public NotImplementedException(String message) {
        this(message, (String)null);
    }

    public NotImplementedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
