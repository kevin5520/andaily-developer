package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-9-12
 *
 * @author Shengzhao Li
 */
public class SprintBacklogsDto extends AbstractDTO {

    private String sprintGuid;
    private List<BacklogDto> backlogs = new ArrayList<BacklogDto>();
    private int budgetBacklogsTime;

    public SprintBacklogsDto() {
    }

    public SprintBacklogsDto(Sprint sprint) {
        this.sprintGuid = sprint.guid();
        this.backlogs = BacklogDto.toDtos(sprint.backlogs());
        this.initialBudgetBacklogsTime();
    }

    private void initialBudgetBacklogsTime() {
        int total = 0;
        for (BacklogDto backlog : backlogs) {
            total += backlog.getEstimateTime();
        }
        this.budgetBacklogsTime = total;
    }

    public int getBudgetBacklogsTime() {
        return budgetBacklogsTime;
    }

    public void setBudgetBacklogsTime(int budgetBacklogsTime) {
        this.budgetBacklogsTime = budgetBacklogsTime;
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public void setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
    }

    public List<BacklogDto> getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(List<BacklogDto> backlogs) {
        this.backlogs = backlogs;
    }
}
