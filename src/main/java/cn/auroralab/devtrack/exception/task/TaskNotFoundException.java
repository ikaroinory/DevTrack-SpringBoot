package cn.auroralab.devtrack.exception.task;

import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.exception.ResponseException;

public class TaskNotFoundException extends ResponseException {
    public TaskNotFoundException() {
        super(
                "Task not found.",
                StatusCode.TASK_NOT_FOUND
        );
    }
}
