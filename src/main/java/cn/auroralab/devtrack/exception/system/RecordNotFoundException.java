package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class RecordNotFoundException extends ResponseException {
    public RecordNotFoundException() {
        super(
                "Record not found.",
                StatusCode.RECORD_NOT_FOUND
        );
    }
}
