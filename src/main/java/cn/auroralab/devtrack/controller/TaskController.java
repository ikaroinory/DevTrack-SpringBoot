package cn.auroralab.devtrack.controller;

import cn.auroralab.devtrack.annotation.SkipTokenVerification;
import cn.auroralab.devtrack.dto.HeatMapData;
import cn.auroralab.devtrack.dto.TaskDTO;
import cn.auroralab.devtrack.dto.TaskMemberDTO;
import cn.auroralab.devtrack.enumeration.Priority;
import cn.auroralab.devtrack.enumeration.SourceOfDemand;
import cn.auroralab.devtrack.enumeration.StatusCode;
import cn.auroralab.devtrack.enumeration.TaskType;
import cn.auroralab.devtrack.exception.ResponseException;
import cn.auroralab.devtrack.form.NewTaskForm;
import cn.auroralab.devtrack.form.UpdateTaskTimeForm;
import cn.auroralab.devtrack.service.TaskService;
import cn.auroralab.devtrack.util.JwtUtils;
import cn.auroralab.devtrack.util.PageInformation;
import cn.auroralab.devtrack.vo.ResponseVO;
import cn.auroralab.devtrack.vo.TaskStatisticsVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @PostMapping("/new")
    public StatusCode create(@RequestHeader(value = "Authorization") String authorization, @RequestBody NewTaskForm form) {
        String creatorUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.newTask(creatorUUID, form);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/getOnePageFromProject")
    public ResponseVO<PageInformation<TaskDTO>> getTaskList(String projectUUID, int pageNum, int pageSize) {
        StatusCode statusCode = StatusCode.SUCCESS;
        PageInformation<TaskDTO> pageInformation = null;

        try {
            pageInformation = taskService.getTaskList(projectUUID, pageNum, pageSize);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, pageInformation);
    }

    @GetMapping("/getTaskMembers")
    public ResponseVO<List<TaskMemberDTO>> getTaskMemberList(String taskUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        List<TaskMemberDTO> list = null;

        try {
            list = taskService.getTaskMemberList(taskUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }

    @GetMapping("/getHeatMap")
    @SkipTokenVerification
    public ResponseVO<List<HeatMapData>> getHeatMap(String userUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        List<HeatMapData> list = null;

        try {
            list = taskService.getTaskCountFinishedInThePastYear(userUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, list);
    }

    @PostMapping("/updateTitle")
    public StatusCode updateTitle(@RequestHeader(value = "Authorization") String authorization, String taskUUID, String title) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateTitle(requesterUUID, taskUUID, title);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updatePrincipal")
    public StatusCode updatePrincipal(@RequestHeader(value = "Authorization") String authorization, String taskUUID, String principalUUID) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updatePrincipal(requesterUUID, taskUUID, principalUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateStartTime")
    public StatusCode updateStartTime(@RequestHeader(value = "Authorization") String authorization, @RequestBody UpdateTaskTimeForm form) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateStartTime(requesterUUID, form.getTaskUUID(), form.getTime());
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateDeadline")
    public StatusCode updateDeadline(@RequestHeader(value = "Authorization") String authorization, @RequestBody UpdateTaskTimeForm form) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateDeadline(requesterUUID, form.getTaskUUID(), form.getTime());
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateType")
    public StatusCode updateType(@RequestHeader(value = "Authorization") String authorization, String taskUUID, TaskType taskType) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateTaskType(requesterUUID, taskUUID, taskType);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updatePriority")
    public StatusCode updatePriority(@RequestHeader(value = "Authorization") String authorization, String taskUUID, Priority priority) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updatePriority(requesterUUID, taskUUID, priority);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateSourceOfDemand")
    public StatusCode updateSourceOfDemand(@RequestHeader(value = "Authorization") String authorization, String taskUUID, SourceOfDemand sourceOfDemand) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateSourceOfDemand(requesterUUID, taskUUID, sourceOfDemand);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateDescription")
    public StatusCode updateDescription(@RequestHeader(value = "Authorization") String authorization, String taskUUID, String description) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateDescription(requesterUUID, taskUUID, description);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/updateMembers")
    public StatusCode updateMembers(@RequestHeader(value = "Authorization") String authorization, String taskUUID, @RequestParam List<String> memberUUIDList) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.updateMembers(requesterUUID, taskUUID, memberUUIDList);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/finish")
    public StatusCode finish(@RequestHeader(value = "Authorization") String authorization, String taskUUID, boolean finished) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.finish(requesterUUID, taskUUID, finished);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @PostMapping("/delete")
    public StatusCode delete(@RequestHeader(value = "Authorization") String authorization, String taskUUID) {
        String requesterUUID = JwtUtils.getUserUUID(authorization);

        StatusCode statusCode = StatusCode.SUCCESS;

        try {
            taskService.delete(requesterUUID, taskUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return statusCode;
    }

    @GetMapping("/getTaskStatistics")
    public ResponseVO<TaskStatisticsVO> getTaskStatistics(String projectUUID) {
        StatusCode statusCode = StatusCode.SUCCESS;
        TaskStatisticsVO taskStatisticsVO = null;

        try {
            taskStatisticsVO = taskService.getTaskStatistics(projectUUID);
        } catch (ResponseException e) {
            statusCode = e.statusCode;
        }

        return new ResponseVO<>(statusCode, taskStatisticsVO);
    }
}
