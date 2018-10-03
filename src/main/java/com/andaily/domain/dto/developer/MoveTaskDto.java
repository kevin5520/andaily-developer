package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.SimpleDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-10-9
 *
 * @author Shengzhao Li
 */
public class MoveTaskDto extends AbstractDTO {

    private String taskNumber;
    private String taskName;

    private String targetSprintGuid;
    private List<SimpleDto> targetSprints = new ArrayList<SimpleDto>();

    public MoveTaskDto() {
    }

    public MoveTaskDto(SprintTask task) {
        super(task.guid());
        this.taskNumber = task.number();
        this.taskName = task.name();
    }

    public String getTargetSprintGuid() {
        return targetSprintGuid;
    }

    public void setTargetSprintGuid(String targetSprintGuid) {
        this.targetSprintGuid = targetSprintGuid;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<SimpleDto> getTargetSprints() {
        return targetSprints;
    }

    public void setTargetSprints(List<SimpleDto> targetSprints) {
        this.targetSprints = targetSprints;
    }

}
