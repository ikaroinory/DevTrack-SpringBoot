package cn.auroralab.devtrack.exception.vcode;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class VCodeErrorException extends ResponseException {
    public VCodeErrorException() {
        super(
                "V-Code error.",
                StatusCode.VCODE_ERROR
        );
    }
}
