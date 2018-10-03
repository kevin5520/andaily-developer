package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintMeetingType;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.shared.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-10-1
 *
 * @author Shengzhao Li
 */
public class SprintMeetingDto extends AbstractDTO {

    protected String meetingDate;
    protected String content;

    protected String createTime;
    protected SprintMeetingType type = SprintMeetingType.DAILY_STANDING;
    protected SprintDto sprintDto;

    public SprintMeetingDto() {
    }

    public SprintMeetingDto(SprintMeeting sprintMeeting) {
        super(sprintMeeting.guid());
        this.sprintDto = new SprintDto(sprintMeeting.sprint());
        this.createTime = DateUtils.toDateTime(sprintMeeting.createTime());

        this.meetingDate = DateUtils.toDateText(sprintMeeting.meetingDate());
        this.content = sprintMeeting.content();
        this.type = sprintMeeting.type();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public SprintMeetingType getType() {
        return type;
    }

    public void setType(SprintMeetingType type) {
        this.type = type;
    }

    public SprintDto getSprintDto() {
        return sprintDto;
    }

    public void setSprintDto(SprintDto sprintDto) {
        this.sprintDto = sprintDto;
    }

    public static List<SprintMeetingDto> toDtos(List<SprintMeeting> meetings) {
        List<SprintMeetingDto> dtos = new ArrayList<SprintMeetingDto>(meetings.size());
        for (SprintMeeting meeting : meetings) {
            dtos.add(new SprintMeetingDto(meeting));
        }
        return dtos;
    }
}
