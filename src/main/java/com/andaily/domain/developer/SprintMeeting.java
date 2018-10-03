package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-9-22
 *
 * @author Shengzhao Li
 */
public class SprintMeeting extends AbstractDomain {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private Date meetingDate;
    private String content;

    private SprintMeetingType type;

    //The developers which join the meeting
    private List<SprintMeetingDeveloper> joinDevelopers = new ArrayList<SprintMeetingDeveloper>();

    private Sprint sprint;

    public SprintMeeting() {
    }

    public SprintMeeting(Date meetingDate, SprintMeetingType type, String content) {
        this.meetingDate = meetingDate;
        this.type = type;
        this.content = content;
    }

    public Date meetingDate() {
        return meetingDate;
    }

    public String content() {
        return content;
    }

    public SprintMeetingType type() {
        return type;
    }

    public List<SprintMeetingDeveloper> joinDevelopers() {
        return joinDevelopers;
    }

    public SprintMeeting updateSprint(Sprint sprint) {
        this.sprint = sprint;
        return this;
    }

    public Sprint sprint() {
        return sprint;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            sprintRepository.saveSprintMeeting(this);
        } else {
            sprintRepository.updateSprintMeeting(this);
        }
    }

    public SprintMeeting updateType(SprintMeetingType type) {
        this.type = type;
        return this;
    }

    public SprintMeeting updateContent(String content) {
        this.content = content;
        return this;
    }

    public SprintMeeting updateMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
        return this;
    }
}
