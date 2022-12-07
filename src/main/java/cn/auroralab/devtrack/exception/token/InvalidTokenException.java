package cn.auroralab.devtrack.exception.token;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class InvalidTokenException extends ResponseException {
    public InvalidTokenException() {
        super(
                "Invalid token.",
                StatusCode.INVALID_TOKEN
        );
    }
}
