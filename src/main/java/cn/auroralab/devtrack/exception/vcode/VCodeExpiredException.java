package cn.auroralab.devtrack.exception.vcode;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class VCodeExpiredException extends ResponseException {
    public VCodeExpiredException(){
        super(
                "V-Code expired.",
                StatusCode.EXPIRED_VCODE
        );
    }
}
