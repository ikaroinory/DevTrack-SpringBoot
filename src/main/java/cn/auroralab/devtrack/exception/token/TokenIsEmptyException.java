package cn.auroralab.devtrack.exception.token;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class TokenIsEmptyException extends ResponseException {
    public TokenIsEmptyException() {
        super(
                "Token is empty.",
                StatusCode.TOKEN_IS_EMPTY
        );
    }
}
