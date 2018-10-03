package com.andaily.domain.developer.burndown;

import com.andaily.domain.shared.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Date: 13-9-24
 *
 * @author Shengzhao Li
 */
public abstract class BurnDownPoint implements Serializable {

    protected Date date;

    protected BurnDownPoint() {
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsDay() {
        return DateUtils.toDateText(date, DateUtils.DAY_FORMAT);
    }

}
