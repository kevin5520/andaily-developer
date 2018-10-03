package com.andaily.domain.developer.burndown;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Date: 13-9-25
 *
 * @author Shengzhao Li
 */
public class ActualBurnDownPoint extends BurnDownPoint {

    private BigDecimal actualRemainTime;

    private BigDecimal dateFinishedTime;

    public ActualBurnDownPoint() {
    }

    public ActualBurnDownPoint(Date date, BigDecimal actualRemainTime) {
        this.date = date;
        this.actualRemainTime = actualRemainTime;
    }

    public BigDecimal getActualRemainTime() {
        return actualRemainTime;
    }

    public String toString() {
        return "{" +
                "date=" + date +
                ",actualRemainTime=" + actualRemainTime +
                '}';
    }

    public BigDecimal getDateFinishedTime() {
        return dateFinishedTime;
    }

    public void setDateFinishedTime(BigDecimal dateFinishedTime) {
        this.dateFinishedTime = dateFinishedTime;
    }
}
