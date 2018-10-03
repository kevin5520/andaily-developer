package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintPriority;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.operation.SprintTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-8-17
 *
 * @author Shengzhao Li
 */
public class SprintTaskFormDto extends SprintTaskDto {

    private String sprintGuid;
    private String sprintName;
    private boolean addNext;

    private String backlogGuid;
    private List<SprintFormBacklogDto> backlogs = new ArrayList<SprintFormBacklogDto>();

    public SprintTaskFormDto() {
    }

    public SprintTaskFormDto(String guid, String sprintGuid) {
        this.guid = guid;
        this.sprintGuid = sprintGuid;
    }

    public SprintTaskFormDto(SprintTask task) {
        super(task);
        Sprint sprint = task.sprint();
        this.sprintGuid = sprint.guid();
        this.sprintName = sprint.name();
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public List<String> getAvailableTimes() {
        return SprintTimeUtils.availableTaskTimes();
    }

    public SprintPriority[] getPriorities() {
        return SprintPriority.values();
    }

    public String getBacklogGuid() {
        return backlogGuid;
    }

    public void setBacklogGuid(String backlogGuid) {
        this.backlogGuid = backlogGuid;
    }

    public List<SprintFormBacklogDto> getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(List<SprintFormBacklogDto> backlogs) {
        this.backlogs = backlogs;
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public void setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
    }

    public boolean isAddNext() {
        return addNext;
    }

    public void setAddNext(boolean addNext) {
        this.addNext = addNext;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }
}
