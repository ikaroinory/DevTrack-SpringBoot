package cn.auroralab.devtrack.exception.vcode;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class VCodeRecordNotFoundException extends ResponseException {
    public VCodeRecordNotFoundException() {
        super(
                "V-Code record not found.",
                StatusCode.VCODE_RECORD_NOT_FOUND
        );
    }
}
