package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.dto.developer.BacklogDto;
import com.andaily.domain.dto.developer.SprintTaskFormDto;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
public class SprintTaskFormDtoLoader {

    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private final String guid;
    private final String sprintGuid;

    public SprintTaskFormDtoLoader(String guid, String sprintGuid) {
        this.guid = guid;
        this.sprintGuid = sprintGuid;
    }

    public SprintTaskFormDto load() {
        SprintTaskFormDto formDto = new SprintTaskFormDto(guid, sprintGuid);
        List<Backlog> backlogs = backlogRepository.findSprintBacklogs(sprintGuid);
        if (formDto.isNewly()) {
            setFormValues(formDto, backlogs);
        } else {
            formDto = loadAndSetFormValues(backlogs);
        }
        return formDto;
    }

    private SprintTaskFormDto loadAndSetFormValues(List<Backlog> backlogs) {
        SprintTask task = sprintRepository.findTaskByGuid(guid);
        ScrumSecurityChecker.checkSprintTask(task, SprintTaskStatus.CREATED);
        ScrumSecurityChecker.checkSprint(task.sprint(), SprintStatus.CREATED, SprintStatus.PENDING);

        SprintTaskFormDto formDto = new SprintTaskFormDto(task);
        String selectGuid = task.backlog() != null ? task.backlog().guid() : null;
        formDto.setBacklogs(BacklogDto.toSprintFormBacklogDtos(backlogs, selectGuid));
        return formDto;
    }

    private void setFormValues(SprintTaskFormDto formDto, List<Backlog> backlogs) {
        String sprintName = sprintRepository.findSprintName(sprintGuid);
        formDto.setSprintName(sprintName);
        formDto.setBacklogs(BacklogDto.toSprintFormBacklogDtos(backlogs, null));
    }
}
