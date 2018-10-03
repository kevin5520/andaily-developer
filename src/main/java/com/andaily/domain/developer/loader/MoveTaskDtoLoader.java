package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.dto.SimpleDto;
import com.andaily.domain.dto.developer.MoveTaskDto;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * Date: 13-10-9
 *
 * @author Shengzhao Li
 */
public class MoveTaskDtoLoader {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private String taskGuid;

    public MoveTaskDtoLoader(String taskGuid) {
        this.taskGuid = taskGuid;
    }

    public MoveTaskDto load() {
        SprintTask task = sprintRepository.findTaskByGuid(taskGuid);
        ScrumSecurityChecker.checkSprintTask(task, SprintTaskStatus.CANCELED);
        ScrumSecurityChecker.checkSprint(task.sprint(), SprintStatus.FINISHED);

        MoveTaskDto moveTaskDto = new MoveTaskDto(task);

        loadTargetSprints(task, moveTaskDto);
        return moveTaskDto;
    }

    private void loadTargetSprints(SprintTask task, MoveTaskDto moveTaskDto) {
        final Sprint sprint = task.sprint();
        String excludeGuid = sprint.guid();
        final Project project = sprint.project();
        List<Sprint> sprintList = sprintRepository.findExcludeGuidAndStatusSprints(excludeGuid, project.guid(), SprintStatus.CREATED, SprintStatus.PENDING);

        List<SimpleDto> targetSprints = SimpleDto.toSprintSimpleDtos(sprintList);
        moveTaskDto.setTargetSprints(targetSprints);
    }
}
