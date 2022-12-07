package cn.auroralab.devtrack.exception.user;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class UserExistedException extends ResponseException {
    public UserExistedException() {
        super(
                "User existed.",
                StatusCode.USER_EXISTED
        );
    }
}
