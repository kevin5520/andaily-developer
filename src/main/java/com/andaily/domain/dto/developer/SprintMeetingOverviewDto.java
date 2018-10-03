package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintMeetingType;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.shared.grouper.GroupResult;
import com.andaily.domain.shared.grouper.impl.SprintSimpleDtoStatusGrouper;
import com.andaily.domain.shared.paginated.DefaultPaginated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-10-2
 *
 * @author Shengzhao Li
 */
public class SprintMeetingOverviewDto extends DefaultPaginated<SprintMeetingDto> {

    private String sprintGuid;
    private List<SprintSimpleDto> sprintDtos = new ArrayList<SprintSimpleDto>();

    private SprintMeetingType type;

    private int dailyStandingMeetings;
    private int planningMeetings;
    private int reviewMeetings;
    private int retrospectiveMeetings;

    public SprintMeetingOverviewDto() {
    }

    public List<GroupResult<SprintStatus, SprintSimpleDto>> getSprintDtosGroupResults() {
        SprintSimpleDtoStatusGrouper grouper = new SprintSimpleDtoStatusGrouper(sprintDtos);
        return grouper.group().getGroupResults();
    }

    public Map<String, Object> queryParams() {
        Map<String, Object> map = super.defaultQueryMap();
        map.put("type", type);
        map.put("sprintGuid", sprintGuid);
        return map;
    }

    @Override
    public void afterLoad() {
        if (type != null) {
            switch (type) {
                case DAILY_STANDING:
                    this.dailyStandingMeetings = totalSize;
                    break;
                case RETROSPECTIVE:
                    this.retrospectiveMeetings = totalSize;
                    break;
                case SPRINT_PLANNING:
                    this.planningMeetings = totalSize;
                    break;
                case SPRINT_REVIEW:
                    this.reviewMeetings = totalSize;
                    break;
            }
        }
    }

    public int getDailyStandingMeetings() {
        return dailyStandingMeetings;
    }

    public SprintMeetingOverviewDto setDailyStandingMeetings(int dailyStandingMeetings) {
        this.dailyStandingMeetings = dailyStandingMeetings;
        return this;
    }

    public int getPlanningMeetings() {
        return planningMeetings;
    }

    public SprintMeetingOverviewDto setPlanningMeetings(int planningMeetings) {
        this.planningMeetings = planningMeetings;
        return this;
    }

    public int getReviewMeetings() {
        return reviewMeetings;
    }

    public SprintMeetingOverviewDto setReviewMeetings(int reviewMeetings) {
        this.reviewMeetings = reviewMeetings;
        return this;
    }

    public int getRetrospectiveMeetings() {
        return retrospectiveMeetings;
    }

    public SprintMeetingOverviewDto setRetrospectiveMeetings(int retrospectiveMeetings) {
        this.retrospectiveMeetings = retrospectiveMeetings;
        return this;
    }

    public SprintMeetingType[] getTypes() {
        return SprintMeetingType.values();
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public void setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
    }

    public List<SprintSimpleDto> getSprintDtos() {
        return sprintDtos;
    }

    public void setSprintDtos(List<SprintSimpleDto> sprintDtos) {
        this.sprintDtos = sprintDtos;
    }

    public SprintMeetingType getType() {
        return type;
    }

    public void setType(SprintMeetingType type) {
        this.type = type;
    }
}
