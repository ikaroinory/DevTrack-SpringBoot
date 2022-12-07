package cn.auroralab.devtrack.exception.role;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class RoleNotFoundException extends ResponseException {
    public RoleNotFoundException() {
        super(
                "Role not found.",
                StatusCode.ROLE_NOT_FOUND
        );
    }
}
