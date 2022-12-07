package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class InvalidParametersException extends ResponseException {
    public InvalidParametersException() {
        super(
                "Invalid parameters.",
                StatusCode.INVALID_PARAMS
        );
    }
}
