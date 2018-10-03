package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

/**
 * Date: 13-11-12
 *
 * @author Shengzhao Li
 */
public class FinishSprintTaskDto extends AbstractDTO {

    private String usedTime;
    private boolean larger4Hours;
    private String comment;

    public FinishSprintTaskDto() {
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public boolean isLarger4Hours() {
        return larger4Hours;
    }

    public void setLarger4Hours(boolean larger4Hours) {
        this.larger4Hours = larger4Hours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
