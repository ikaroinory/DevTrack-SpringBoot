package cn.auroralab.devtrack.exception;

import cn.auroralab.devtrack.enumeration.StatusCode;

public class ResponseException extends BaseRuntimeException {
    public final StatusCode statusCode;

    public ResponseException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
