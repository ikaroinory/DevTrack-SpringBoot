package cn.auroralab.devtrack.exception.user;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class WrongPasswordException extends ResponseException {
    public WrongPasswordException() {
        super(
                "Wrong password.",
                StatusCode.PASSWORD_ERROR
        );
    }
}
