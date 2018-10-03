package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintManageBacklogsDto extends AbstractDTO {

    private SprintSimpleDto sprintDto;


    private List<BacklogSelectedDto> sprintBacklogs = new ArrayList<>();
    private List<BacklogDto> unusedBacklogs = new ArrayList<>();


    public SprintManageBacklogsDto() {
    }

    public SprintSimpleDto getSprintDto() {
        return sprintDto;
    }

    public SprintManageBacklogsDto setSprintDto(SprintSimpleDto sprintDto) {
        this.sprintDto = sprintDto;
        return this;
    }

    public int getSprintBacklogsSize() {
        return sprintBacklogs.size();
    }

    public int getUnusedBacklogsSize() {
        return unusedBacklogs.size();
    }

    public List<BacklogSelectedDto> getSprintBacklogs() {
        return sprintBacklogs;
    }

    public SprintManageBacklogsDto setSprintBacklogs(List<BacklogSelectedDto> sprintBacklogs) {
        this.sprintBacklogs = sprintBacklogs;
        return this;
    }

    public List<BacklogDto> getUnusedBacklogs() {
        return unusedBacklogs;
    }

    public SprintManageBacklogsDto setUnusedBacklogs(List<BacklogDto> unusedBacklogs) {
        this.unusedBacklogs = unusedBacklogs;
        return this;
    }
}