package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintMeetingType;
import com.andaily.domain.shared.DateUtils;

/**
 * Date: 13-10-1
 *
 * @author Shengzhao Li
 */
public class SprintMeetingFormDto extends SprintMeetingDto {

    private boolean backToOverview = false;

    public SprintMeetingFormDto() {
    }

    public SprintMeetingFormDto(SprintMeeting sprintMeeting) {
        super(sprintMeeting);
    }

    public SprintMeetingFormDto(String guid, String sprintGuid) {
        this.guid = guid;
        this.meetingDate = DateUtils.toDateText(DateUtils.now());
        this.sprintDto = new SprintDto();
        this.sprintDto.setGuid(sprintGuid);
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public SprintMeetingType[] getTypes() {
        return SprintMeetingType.values();
    }

    public boolean isBackToOverview() {
        return backToOverview;
    }

    public void setBackToOverview(boolean backToOverview) {
        this.backToOverview = backToOverview;
    }
}
