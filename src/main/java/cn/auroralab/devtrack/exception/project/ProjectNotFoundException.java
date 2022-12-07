package cn.auroralab.devtrack.exception.project;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class ProjectNotFoundException extends ResponseException {
    public ProjectNotFoundException() {
        super(
                "Project not found.",
                StatusCode.PROJECT_NOT_FOUND
        );
    }
}
