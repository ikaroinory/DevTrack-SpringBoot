package cn.auroralab.devtrack.exception.user;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class UserNotFoundException extends ResponseException {
    public UserNotFoundException() {
        super(
                "User not found.",
                StatusCode.USER_NOT_FOUND
        );
    }
}
