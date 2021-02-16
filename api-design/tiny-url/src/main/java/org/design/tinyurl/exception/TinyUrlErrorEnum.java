package org.design.tinyurl.exception;

public enum TinyUrlErrorEnum {
    ALIAS_NOT_AVAILABLE("Alias not available", 400),
    INTERNAL_SERVER_ERROR("Internal server error", 500),
    NO_SUCH_ALIAS_EXISTS("No such alias exists", 404),
    NO_SUCH_USER_EXISTS("No such user exists", 404);
    private static final int DEFAULT_HTTP_ERROR_CODE = 400;
    private String message;
    private int httpErrorCode;

    TinyUrlErrorEnum(String message) {
        this.message = message;
        this.httpErrorCode = DEFAULT_HTTP_ERROR_CODE;
    }

    TinyUrlErrorEnum(String message, int httpErrorCode) {
        this.message = message;
        this.httpErrorCode = httpErrorCode;
    }

    public String getMessage() {
        return message;
    }
}
