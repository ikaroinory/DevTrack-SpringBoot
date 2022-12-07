package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class RequiredParametersIsEmptyException extends ResponseException {
    public RequiredParametersIsEmptyException() {
        super(
                "Required parameters is empty.",
                StatusCode.REQUIRED_PARAMS_IS_EMPTY
        );
    }
}
