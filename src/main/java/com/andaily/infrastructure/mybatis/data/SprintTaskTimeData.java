package com.andaily.infrastructure.mybatis.data;

import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class SprintTaskTimeData extends AbstractDTO {

    private int estimateTime;
    private int actualUsedTime;

    public SprintTaskTimeData() {
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
    }

    public int getActualUsedTime() {
        return actualUsedTime;
    }

    public void setActualUsedTime(int actualUsedTime) {
        this.actualUsedTime = actualUsedTime;
    }
}