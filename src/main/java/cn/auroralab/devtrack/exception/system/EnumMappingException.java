package cn.auroralab.devtrack.exception.system;

import cn.auroralab.devtrack.exception.BaseRuntimeException;

public class EnumMappingException extends BaseRuntimeException {
    public EnumMappingException() {
        super("Enumeration mapping exception.");
    }
}
