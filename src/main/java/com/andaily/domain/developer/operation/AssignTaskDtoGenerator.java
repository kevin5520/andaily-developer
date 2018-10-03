package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.SprintTaskStatus;
import com.andaily.domain.dto.developer.AssignTaskDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class AssignTaskDtoGenerator {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private String guid;

    public AssignTaskDtoGenerator(String guid) {
        this.guid = guid;
    }

    public AssignTaskDto generate() {
        SprintTask task = sprintRepository.findTaskByGuid(guid);

        checking(task);
        final List<Developer> members = task.sprint().executeTeam().members();
        final List<DeveloperDto> developerDtos = DeveloperDto.toDeveloperDtos(members);

        final AssignTaskDto assignTaskDto = new AssignTaskDto()
                .setDevelopers(developerDtos)
                .setTaskNumber(task.number());

        final User executor = task.executor();
        if (executor != null) {
            assignTaskDto.setExecutorGuid(executor.guid())
                    .setExecutorName(executor.displayName());
        }
        return assignTaskDto;
    }

    private void checking(SprintTask task) {
        ScrumSecurityChecker.checkSprintTask(task, SprintTaskStatus.CREATED);
        ScrumSecurityChecker.checkSprint(task.sprint(), SprintStatus.CREATED, SprintStatus.PENDING);
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        if (!ScrumTerm.MASTER.equals(scrumTerm)) {
            throw new IllegalStateException("Warning: Illegal operation, you are not [MASTER]");
        }
    }
}