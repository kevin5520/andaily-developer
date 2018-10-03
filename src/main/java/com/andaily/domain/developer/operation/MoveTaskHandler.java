package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.SprintTaskMoveRecord;
import com.andaily.domain.dto.developer.MoveTaskDto;
import com.andaily.domain.log.LogHandler;
import com.andaily.web.context.BeanProvider;
import org.apache.log4j.Logger;

/**
 * Date: 13-10-9
 *
 * @author Shengzhao Li
 */
public class MoveTaskHandler {

    private static final Logger LOGGER = Logger.getLogger(MoveTaskHandler.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private MoveTaskDto moveTaskDto;

    public MoveTaskHandler(MoveTaskDto moveTaskDto) {
        this.moveTaskDto = moveTaskDto;
    }

    //return the task sprint old guid
    public String handle() {
        SprintTask moveTask = sprintRepository.findTaskByGuid(moveTaskDto.getGuid());
        Sprint oldSprint = moveTask.sprint();
        Sprint targetSprint = sprintRepository.findByGuid(moveTaskDto.getTargetSprintGuid());

        moveTask.updateSprint(targetSprint);
        moveTask.updateBacklog(null);//Remove the reference backlog.

        moveTask.saveOrUpdate();
        record(oldSprint, targetSprint, moveTask);
        LOGGER.debug("Move the task " + moveTask + " to target sprint " + targetSprint + " already finished.");
        LogHandler.createSprintActivityLog("move the task [" + moveTask.name() + "] from old sprint [" + oldSprint.name() + "] to sprint [" + targetSprint.name() + "]", oldSprint);
        return oldSprint.guid();
    }

    private void record(Sprint oldSprint, Sprint targetSprint, SprintTask moveTask) {
        SprintTaskMoveRecord record = new SprintTaskMoveRecord(oldSprint, targetSprint, moveTask);
        sprintRepository.saveSprintTaskMoveRecord(record);
    }
}
