package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.*;
import com.andaily.domain.dto.developer.SprintTaskFormDto;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
public class SprintTaskFormPersister {

    private static LogHelper log = LogHelper.create(SprintTaskFormPersister.class);

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);

    private SprintTaskFormDto sprintTaskFormDto;

    public SprintTaskFormPersister(SprintTaskFormDto sprintTaskFormDto) {
        this.sprintTaskFormDto = sprintTaskFormDto;
    }

    public String persist() {
        if (sprintTaskFormDto.isNewly()) {
            return createSprintTask();
        } else {
            return updateSprintTask();
        }
    }

    private String updateSprintTask() {
        SprintTask existTask = sprintRepository.findTaskByGuid(sprintTaskFormDto.getGuid());
        existTask.updateName(sprintTaskFormDto.getName()).updateDescription(sprintTaskFormDto.getDescription());
        existTask.updatePriority(sprintTaskFormDto.getPriority()).updateUrgent(sprintTaskFormDto.isUrgent());

        int estTime = SprintTimeUtils.taskTimeAsMinute(sprintTaskFormDto.getEstimateTime());
        existTask.updateEstimateTime(estTime);
        //update backlog
        updateBacklog(existTask);
        existTask.saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " update the SprintTask [" + existTask.guid() + "]");
        return existTask.guid();
    }

    private String createSprintTask() {
        SprintTask newTask = newTask();
        //refer sprint
        Sprint sprint = sprintRepository.findByGuid(sprintTaskFormDto.getSprintGuid());
        newTask.updateSprint(sprint);
        //task number:  max task number in project  plus 1
        newTask.number(sprint.project().maxTaskNumber() + 1);
        //refer backlog
        updateBacklog(newTask);
        newTask.saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " create the SprintTask [" + newTask.guid() + "]");
        LogHandler.createSprintActivityLog("create the task [" + newTask.name() + "]", sprintRepository.findTaskByGuid(newTask.guid()));
        return newTask.guid();
    }

    private void updateBacklog(SprintTask task) {
        String backlogGuid = sprintTaskFormDto.getBacklogGuid();
        if (StringUtils.isNotEmpty(backlogGuid)) {
            Backlog backlog = backlogRepository.findByGuid(backlogGuid);
            task.updateBacklog(backlog);
        }
    }

    private SprintTask newTask() {
        int estTime = SprintTimeUtils.taskTimeAsMinute(sprintTaskFormDto.getEstimateTime());
        SprintTask newTask = new SprintTask(sprintTaskFormDto.getName(), estTime,
                sprintTaskFormDto.getPriority(), sprintTaskFormDto.isUrgent());
        newTask.updateDescription(sprintTaskFormDto.getDescription())
                .updateCreator(SecurityUtils.currUser());
        return newTask;
    }
}
