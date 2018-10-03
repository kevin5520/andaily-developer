package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.shared.DateUtils;

/**
 * Date: 13-8-21
 *
 * @author Shengzhao Li
 */
public class CheckSprintStartDto extends SprintDto {

    private String today;

    public CheckSprintStartDto() {
    }

    public CheckSprintStartDto(Sprint sprint) {
        super(sprint);
        this.today = DateUtils.toDateText(DateUtils.now());
    }

    public boolean isPass() {
        return this.today.equals(this.startDate);
    }

    public String getToday() {
        return today;
    }
}
