package cn.auroralab.devtrack.exception.role;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class RoleRecordExistException extends ResponseException {
    public RoleRecordExistException() {
        super(
                "The number of member with role is not zero.",
                StatusCode.ROLE_RECORD_EXIST
        );
    }
}
