package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class MoveSprintBacklogDto extends AbstractDTO {

    private String sprintGuid;
    private String backlogGuid;

    private boolean inverse;


    public MoveSprintBacklogDto() {
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public void setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
    }

    public String getBacklogGuid() {
        return backlogGuid;
    }

    public void setBacklogGuid(String backlogGuid) {
        this.backlogGuid = backlogGuid;
    }

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }
}