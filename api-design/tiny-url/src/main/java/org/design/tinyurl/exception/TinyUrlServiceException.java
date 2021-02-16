package org.design.tinyurl.exception;

public class TinyUrlServiceException extends Exception {
    private TinyUrlErrorEnum errorCode;
    private String errorCodeInfo;

    public TinyUrlServiceException(TinyUrlErrorEnum errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public TinyUrlServiceException(TinyUrlErrorEnum errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
