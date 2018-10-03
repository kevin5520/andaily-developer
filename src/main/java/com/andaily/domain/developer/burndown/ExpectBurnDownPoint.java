package com.andaily.domain.developer.burndown;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Date: 13-9-25
 *
 * @author Shengzhao Li
 */
public class ExpectBurnDownPoint extends BurnDownPoint {

    private BigDecimal expectRemainTime;

    public ExpectBurnDownPoint() {
    }

    public ExpectBurnDownPoint(Date date, BigDecimal expectRemainTime) {
        this.date = date;
        this.expectRemainTime = expectRemainTime;
    }

    public BigDecimal getExpectRemainTime() {
        return expectRemainTime;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ",expectRemainTime=" + expectRemainTime +
                '}';
    }
}
