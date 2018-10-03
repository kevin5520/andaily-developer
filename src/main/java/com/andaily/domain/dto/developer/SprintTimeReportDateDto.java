package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.shared.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class SprintTimeReportDateDto extends AbstractDTO {

    private Date date;
    private String dateAsText;
    private BigDecimal times = BigDecimal.ZERO;

    public SprintTimeReportDateDto() {
    }

    public SprintTimeReportDateDto(Date date) {
        this.date = date;
    }

    public SprintTimeReportDateDto(BigDecimal times) {
        this.times = times;
    }

    public String getShowDate() {
        if (date != null) {
            return DateUtils.toDateText(date, "MM.dd");
        } else {
            return dateAsText;
        }
    }

    public boolean isToday() {
        final String now = DateUtils.toDateText(DateUtils.now());
        if (date != null) {
            return DateUtils.toDateText(date).equals(now);
        }
        if (StringUtils.isNotEmpty(dateAsText)) {
            return dateAsText.equals(now);
        }
        return false;
    }

    public BigDecimal getTimes() {
        return times;
    }

    public void setTimes(BigDecimal times) {
        this.times = times;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateAsText() {
        return dateAsText;
    }

    public void setDateAsText(String dateAsText) {
        this.dateAsText = dateAsText;
    }
}