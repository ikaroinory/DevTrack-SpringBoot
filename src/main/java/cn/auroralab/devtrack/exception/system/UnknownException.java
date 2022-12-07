package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class UnknownException extends ResponseException {
    public UnknownException() {
        super(
                "Unknown exception.",
                StatusCode.UNKNOWN
        );
    }
}
