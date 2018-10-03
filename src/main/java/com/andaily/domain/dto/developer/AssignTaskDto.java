package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.user.DeveloperDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class AssignTaskDto extends AbstractDTO {

    private String taskNumber;
    //already assigned executor
    private String executorGuid;
    private String executorName;

    private List<DeveloperDto> developers = new ArrayList<>();

    public AssignTaskDto() {
        super();
    }

    public String getExecutorName() {
        return executorName;
    }

    public AssignTaskDto setExecutorName(String executorName) {
        this.executorName = executorName;
        return this;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public AssignTaskDto setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
        return this;
    }

    public String getExecutorGuid() {
        return executorGuid;
    }

    public AssignTaskDto setExecutorGuid(String executorGuid) {
        this.executorGuid = executorGuid;
        return this;
    }

    public List<DeveloperDto> getDevelopers() {
        return developers;
    }

    public AssignTaskDto setDevelopers(List<DeveloperDto> developers) {
        this.developers = developers;
        return this;
    }
}