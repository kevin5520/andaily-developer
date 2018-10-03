package com.andaily.service.impl;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.burndown.BurnDown;
import com.andaily.domain.developer.burndown.BurnDownGenerator;
import com.andaily.domain.developer.loader.*;
import com.andaily.domain.developer.operation.*;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.*;
import com.andaily.domain.dto.log.SprintActivityOverviewDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import com.andaily.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 */
public class ScrumServiceImpl implements ScrumService {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public DeveloperOverviewDto loadDeveloperOverviewDto(DeveloperOverviewDto overviewDto) {
        DeveloperOverviewDtoLoader developerOverviewDtoLoader = new DeveloperOverviewDtoLoader(overviewDto);
        return developerOverviewDtoLoader.load();
    }

    @Override
    public SprintFormDto loadSprintFormDto(String guid, String projectGuid) {
        SprintFormDtoLoader sprintFormDtoLoader = new SprintFormDtoLoader(guid, projectGuid);
        return sprintFormDtoLoader.load();
    }

    @Override
    public String persistSprintFormDto(SprintFormDto sprintFormDto) {
        SprintFormPersister sprintFormPersister = new SprintFormPersister(sprintFormDto);
        return sprintFormPersister.persist();
    }

    @Override
    public boolean loadExistSprintName(String name) {
        Sprint sprint = sprintRepository.findByName(name);
        return sprint != null;
    }

    @Override
    public SprintTaskFormDto loadSprintTaskFormDto(String guid, String sprintGuid) {
        SprintTaskFormDtoLoader loader = new SprintTaskFormDtoLoader(guid, sprintGuid);
        return loader.load();
    }

    @Override
    public String persistSprintTaskFormDto(SprintTaskFormDto sprintTaskFormDto) {
        SprintTaskFormPersister taskFormDtoPersister = new SprintTaskFormPersister(sprintTaskFormDto);
        return taskFormDtoPersister.persist();
    }

    @Override
    public String startTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        task.start().saveOrUpdate();
        return task.sprint().guid();
    }

    @Override
    public String archiveTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return task.archiveMe();
    }

    @Override
    public String cancelTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return task.cancelIt();
    }

    @Override
    public String revertTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        task.revert().saveOrUpdate();
        return task.sprint().guid();
    }

    @Override
    public SprintTaskDto loadSprintTaskDto(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return new SprintTaskDto(task);
    }

    @Override
    public String finishTask(FinishSprintTaskDto finishSprintTaskDto) {
        FinishSprintTaskHandler handler = new FinishSprintTaskHandler(finishSprintTaskDto);
        return handler.handle();
    }

    @Override
    public String restoreTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        task.restore().saveOrUpdate();
        return task.sprint().guid();
    }

    @Override
    public String startSprint(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        sprint.start().saveOrUpdate();
        return sprint.guid();
    }

    @Override
    public CheckSprintStartDto loadCheckSprintStartDto(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        return new CheckSprintStartDto(sprint);
    }

    @Override
    public String archiveSprint(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        sprint.archiveMe();
        return sprint.project().guid();
    }

    @Override
    public CheckSprintFinishDto loadCheckSprintFinishDto(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        return new CheckSprintFinishDto(sprint);
    }

    @Override
    public void finishSprint(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        sprint.finish().saveOrUpdate();
    }

    @Override
    public SprintOverviewDto loadSprintOverviewDto(SprintOverviewDto sprintOverviewDto) {
        SprintOverviewDtoLoader loader = new SprintOverviewDtoLoader(sprintOverviewDto);
        return loader.load();
    }

    @Override
    public SprintBacklogsDto loadSprintBacklogsDto(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        return new SprintBacklogsDto(sprint);
    }

    @Override
    public SprintFormBacklogDto loadTaskBacklogDto(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return new SprintFormBacklogDto(task.backlog());
    }

    @Override
    public BurnDownDetailsDto loadBurnDownDetailsDto(String sprintGuid) {
        Sprint sprint = sprintRepository.findByGuid(sprintGuid);
        BurnDownGenerator burnDownGenerator = new BurnDownGenerator(sprint);
        BurnDown burnDown = burnDownGenerator.generate();
        return new BurnDownDetailsDto(new BurnDownChartWrapper(burnDown));
    }

    @Override
    public SprintMeetingFormDto loadSprintMeetingFormDto(String guid, String sprintGuid) {
        SprintMeetingFormDto formDto = new SprintMeetingFormDto(guid, sprintGuid);
        if (!formDto.isNewly()) {
            SprintMeeting sprintMeeting = sprintRepository.findMeetingByGuid(guid);
            formDto = new SprintMeetingFormDto(sprintMeeting);
        }
        return formDto;
    }

    @Override
    public String persistSprintMeetingFormDto(SprintMeetingFormDto sprintMeetingFormDto) {
        SprintMeetingFormPersister persister = new SprintMeetingFormPersister(sprintMeetingFormDto);
        return persister.persist();
    }

    @Override
    public SprintMeetingOverviewDto loadSprintMeetingOverviewDto(SprintMeetingOverviewDto overviewDto) {
        SprintMeetingOverviewDtoLoader loader = new SprintMeetingOverviewDtoLoader(overviewDto);
        return loader.load();
    }

    @Override
    public SprintMeetingDto loadSprintMeetingDto(String guid) {
        SprintMeeting meeting = sprintRepository.findMeetingByGuid(guid);
        return new SprintMeetingDto(meeting);
    }

    @Override
    public MoveTaskDto loadMoveTaskDto(String taskGuid) {
        MoveTaskDtoLoader moveTaskDtoLoader = new MoveTaskDtoLoader(taskGuid);
        return moveTaskDtoLoader.load();
    }

    @Override
    public String moveTask(MoveTaskDto moveTaskDto) {
        MoveTaskHandler moveTaskHandler = new MoveTaskHandler(moveTaskDto);
        return moveTaskHandler.handle();
    }

    @Override
    public String setDefaultSprint(String sprintGuid) {
        Sprint sprint = sprintRepository.findByGuid(sprintGuid);
        return sprint.markDefault().project().guid();
    }

    @Override
    public StartTaskCheckingResult checkingStartTask(String guid) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return task.startChecking();
    }

    @Override
    public List<SimpleProjectData> loadAvailableSimpleProjects() {
        final User currUser = SecurityUtils.currUser();
        if (currUser.scrumTerm().isProductOwner()) {
            return projectRepository.findSimpleProjectsByDeveloper((Developer) currUser);
        } else {
            return projectRepository.findSimpleProjectsByTeam(currUser.team());
        }
    }

    @Override
    public AssignTaskDto loadAssignTaskDto(String guid) {
        AssignTaskDtoGenerator generator = new AssignTaskDtoGenerator(guid);
        return generator.generate();
    }

    @Override
    public String assignTask(String taskGuid, String executorGuid) {
        SprintTask task = sprintRepository.findTaskByGuid(taskGuid);
        return task.assignTaskExecutor(executorGuid);
    }

    @Override
    public SprintTimeReportDto loadSprintTimeReportDto(SprintTimeReportDto timeReportDto) {
        SprintTimeReportDtoGenerator generator = new SprintTimeReportDtoGenerator(timeReportDto);
        return generator.generate();
    }

    @Override
    public String cancelDefaultSprint(String guid) {
        Sprint sprint = sprintRepository.findByGuid(guid);
        return sprint.cancelDefaultSprint();
    }

    @Override
    public SprintManageBacklogsDto loadSprintManageBacklogsDto(String guid) {
        SprintManageBacklogsDtoGenerator generator = new SprintManageBacklogsDtoGenerator(guid);
        return generator.generate();
    }

    @Override
    public boolean moveSprintBacklog(MoveSprintBacklogDto moveSprintBacklogDto) {
        MoveSprintBacklogHandler moveSprintBacklogHandler = new MoveSprintBacklogHandler(moveSprintBacklogDto);
        return moveSprintBacklogHandler.handle();
    }

    @Override
    public SprintTaskCommentsDto loadSprintTaskCommentsDto(String guid) {
        final List<SprintTaskComment> comments = sprintRepository.findCommentsByTaskGuid(guid);
        return new SprintTaskCommentsDto(guid)
                .setComments(SprintTaskCommentDto.toDtos(comments));
    }

    @Override
    public SprintTaskCommentDto persistSprintTaskComment(String guid, String content) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        return task.addComment(content);
    }

    @Override
    public boolean deleteSprintTaskComment(String guid) {
        SprintTaskComment comment = sprintRepository.findSprintTaskCommentByGuid(guid);
        return comment.deleteMe();
    }

    @Override
    public SprintActivityOverviewDto loadSprintActivityOverviewDto(SprintActivityOverviewDto activityOverviewDto) {
        SprintActivityOverviewDtoLoader loader = new SprintActivityOverviewDtoLoader(activityOverviewDto);
        return loader.load();
    }
}
