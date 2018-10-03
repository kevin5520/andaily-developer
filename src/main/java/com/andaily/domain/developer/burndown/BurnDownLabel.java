package com.andaily.domain.developer.burndown;

import com.andaily.domain.shared.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Date: 13-9-28
 *
 * @author Shengzhao Li
 */
public class BurnDownLabel implements Serializable {

    private Date date;

    public BurnDownLabel() {
    }

    public BurnDownLabel(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsDay() {
        return DateUtils.toDateText(date, DateUtils.DAY_FORMAT);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
