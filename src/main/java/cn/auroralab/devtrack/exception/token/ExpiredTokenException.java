package cn.auroralab.devtrack.exception.token;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class ExpiredTokenException extends ResponseException {
    public ExpiredTokenException() {
        super(
                "Expired token.",
                StatusCode.EXPIRED_TOKEN
        );
    }
}
