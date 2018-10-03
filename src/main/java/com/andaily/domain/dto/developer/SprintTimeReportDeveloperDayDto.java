package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

import java.math.BigDecimal;

/**
 * @author Shengzhao Li
 */
public class SprintTimeReportDeveloperDayDto extends AbstractDTO {

    private BigDecimal estimateTime = BigDecimal.ZERO;
    private BigDecimal actualUsedTime = BigDecimal.ZERO;


    public SprintTimeReportDeveloperDayDto() {
    }

    public String getShowTimeText() {
        final int result = estimateTime.compareTo(actualUsedTime);
        final String estToString = estimateTime.toString();
        if (result > 0) {
            return estToString + "(-" + estimateTime.subtract(actualUsedTime) + ")";
        } else if (result < 0) {
            return estToString + "(+" + actualUsedTime.subtract(estimateTime) + ")";
        } else {
            return estToString;
        }
    }

    public BigDecimal getEstimateTime() {
        return estimateTime;
    }

    public SprintTimeReportDeveloperDayDto setEstimateTime(BigDecimal estimateTime) {
        this.estimateTime = estimateTime;
        return this;
    }

    public BigDecimal getActualUsedTime() {
        return actualUsedTime;
    }

    public SprintTimeReportDeveloperDayDto setActualUsedTime(BigDecimal actualUsedTime) {
        this.actualUsedTime = actualUsedTime;
        return this;
    }
}