package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.user.Developer;

/**
 * Date: 13-9-23
 * A middle object.
 *
 * @author Shengzhao Li
 */
public class SprintMeetingDeveloper extends AbstractDomain {

    private SprintMeeting meeting;
    private Developer developer;

    public SprintMeetingDeveloper() {
    }

    public SprintMeetingDeveloper(Developer developer, SprintMeeting meeting) {
        this.meeting = meeting;
        this.developer = developer;
    }

    public SprintMeeting meeting() {
        return meeting;
    }

    public Developer developer() {
        return developer;
    }
}
