package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.dto.developer.BacklogDto;
import com.andaily.domain.dto.developer.BacklogSelectedDto;
import com.andaily.domain.dto.developer.SprintManageBacklogsDto;
import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintManageBacklogsDtoGenerator {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private String guid;

    public SprintManageBacklogsDtoGenerator(String guid) {
        this.guid = guid;
    }

    public SprintManageBacklogsDto generate() {
        final Sprint sprint = sprintRepository.findByGuid(guid);
        ScrumSecurityChecker.checkSprint(sprint, SprintStatus.PENDING);

        SprintManageBacklogsDto manageBacklogsDto = new SprintManageBacklogsDto();
        manageBacklogsDto.setSprintDto(new SprintSimpleDto(sprint));

        generateUnusedBacklogs(sprint, manageBacklogsDto);
        generateSprintBacklogs(sprint, manageBacklogsDto);

        return manageBacklogsDto;
    }

    private void generateSprintBacklogs(Sprint sprint, SprintManageBacklogsDto manageBacklogsDto) {
        final List<BacklogSelectedDto> sprintBacklogs = manageBacklogsDto.getSprintBacklogs();
        final List<SprintTask> tasks = sprint.tasks();

        final List<Backlog> backlogs = sprint.backlogs();
        for (Backlog backlog : backlogs) {
            sprintBacklogs.add(new BacklogSelectedDto(backlog)
                    .setSelected(isUsedBacklog(backlog, tasks)));
        }

    }

    private boolean isUsedBacklog(Backlog backlog, List<SprintTask> tasks) {
        for (SprintTask task : tasks) {
            final Backlog backlog1 = task.backlog();
            if (backlog1 != null && backlog1.equals(backlog)) {
                return true;
            }
        }
        return false;
    }

    private void generateUnusedBacklogs(Sprint sprint, SprintManageBacklogsDto manageBacklogsDto) {
        final Project project = sprint.project();
        List<Backlog> backlogList = backlogRepository.findUnusedBacklogsByProject(project);
        manageBacklogsDto.setUnusedBacklogs(BacklogDto.toDtos(backlogList));
    }
}