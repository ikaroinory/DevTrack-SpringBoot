package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class FileTypeException extends ResponseException {
    public FileTypeException() {
        super(
                "File type exception.",
                StatusCode.INVALID_FILETYPE
        );
    }
}
