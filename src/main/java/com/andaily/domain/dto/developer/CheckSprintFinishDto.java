package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.shared.DateUtils;

/**
 * Date: 13-8-27
 *
 * @author Shengzhao Li
 */
public class CheckSprintFinishDto extends SprintDto {

    private boolean todayIsDeadline;
    private boolean allowFinish;

    public CheckSprintFinishDto() {
    }

    public CheckSprintFinishDto(Sprint sprint) {
        super(sprint);
        this.todayIsDeadline = DateUtils.isToday(sprint.deadline());
        this.allowFinish = sprint.allowFinish();
    }

    public boolean isAllowFinish() {
        return allowFinish;
    }

    public boolean isTodayIsDeadline() {
        return todayIsDeadline;
    }
}
