package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class PermissionDeniedException extends ResponseException {
    public PermissionDeniedException() {
        super(
                "Permission denied.",
                StatusCode.PERMISSION_DENIED
        );
    }
}
