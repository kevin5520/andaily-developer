package com.andaily.service;

import com.andaily.domain.dto.developer.*;
import com.andaily.domain.dto.log.SprintActivityOverviewDto;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;

import java.util.List;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 */
public interface ScrumService {

    DeveloperOverviewDto loadDeveloperOverviewDto(DeveloperOverviewDto developerOverviewDto);

    SprintFormDto loadSprintFormDto(String guid, String projectGuid);

    String persistSprintFormDto(SprintFormDto sprintFormDto);

    boolean loadExistSprintName(String name);

    SprintTaskFormDto loadSprintTaskFormDto(String guid, String sprintGuid);

    String persistSprintTaskFormDto(SprintTaskFormDto sprintTaskFormDto);

    String startTask(String guid);

    String archiveTask(String guid);

    String cancelTask(String guid);

    String revertTask(String guid);

    SprintTaskDto loadSprintTaskDto(String guid);

    String finishTask(FinishSprintTaskDto finishSprintTaskDto);

    String restoreTask(String guid);

    String startSprint(String guid);

    CheckSprintStartDto loadCheckSprintStartDto(String guid);

    //return the sprint project guid
    String archiveSprint(String guid);

    CheckSprintFinishDto loadCheckSprintFinishDto(String guid);

    void finishSprint(String guid);

    SprintOverviewDto loadSprintOverviewDto(SprintOverviewDto sprintOverviewDto);

    SprintBacklogsDto loadSprintBacklogsDto(String guid);

    SprintFormBacklogDto loadTaskBacklogDto(String guid);

    BurnDownDetailsDto loadBurnDownDetailsDto(String sprintGuid);

    SprintMeetingFormDto loadSprintMeetingFormDto(String guid, String sprintGuid);

    String persistSprintMeetingFormDto(SprintMeetingFormDto sprintMeetingFormDto);

    SprintMeetingOverviewDto loadSprintMeetingOverviewDto(SprintMeetingOverviewDto overviewDto);

    SprintMeetingDto loadSprintMeetingDto(String guid);

    MoveTaskDto loadMoveTaskDto(String taskGuid);

    String moveTask(MoveTaskDto moveTaskDto);

    String setDefaultSprint(String sprintGuid);

    StartTaskCheckingResult checkingStartTask(String guid);

    List<SimpleProjectData> loadAvailableSimpleProjects();

    AssignTaskDto loadAssignTaskDto(String guid);

    String assignTask(String taskGuid, String executorGuid);

    SprintTimeReportDto loadSprintTimeReportDto(SprintTimeReportDto timeReportDto);

    String cancelDefaultSprint(String guid);

    SprintManageBacklogsDto loadSprintManageBacklogsDto(String guid);

    boolean moveSprintBacklog(MoveSprintBacklogDto moveSprintBacklogDto);

    SprintTaskCommentsDto loadSprintTaskCommentsDto(String guid);

    SprintTaskCommentDto persistSprintTaskComment(String guid, String content);

    boolean deleteSprintTaskComment(String guid);

    SprintActivityOverviewDto loadSprintActivityOverviewDto(SprintActivityOverviewDto activityOverviewDto);
}
