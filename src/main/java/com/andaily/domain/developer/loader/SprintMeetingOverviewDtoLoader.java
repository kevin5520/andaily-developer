package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintMeetingType;
import com.andaily.domain.dto.developer.SprintMeetingDto;
import com.andaily.domain.dto.developer.SprintMeetingOverviewDto;
import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-10-2
 *
 * @author Shengzhao Li
 */
public class SprintMeetingOverviewDtoLoader extends AvailableSprintsLoader {


    private SprintMeetingOverviewDto overviewDto;

    public SprintMeetingOverviewDtoLoader(SprintMeetingOverviewDto overviewDto) {
        this.overviewDto = overviewDto;
    }

    public SprintMeetingOverviewDto load() {
        final List<SprintSimpleDto> sprintSimpleDtos = loadAvailableSprints();
        overviewDto.setSprintDtos(sprintSimpleDtos);

        loadPaginated();
        loadStatistics();
        return overviewDto;
    }

    private void loadStatistics() {
        if (overviewDto.getType() == null) {
            final String sprintGuid = overviewDto.getSprintGuid();
            overviewDto.setDailyStandingMeetings(sprintRepository.totalSprintMeetings(sprintGuid, SprintMeetingType.DAILY_STANDING));
            overviewDto.setPlanningMeetings(sprintRepository.totalSprintMeetings(sprintGuid, SprintMeetingType.SPRINT_PLANNING));
            overviewDto.setRetrospectiveMeetings(sprintRepository.totalSprintMeetings(sprintGuid, SprintMeetingType.RETROSPECTIVE));
            overviewDto.setReviewMeetings(sprintRepository.totalSprintMeetings(sprintGuid, SprintMeetingType.SPRINT_REVIEW));
        }
    }

    private void loadPaginated() {
        final Map<String, Object> map = overviewDto.queryParams();
        overviewDto = overviewDto.load(new PaginatedLoader<SprintMeetingDto>() {
            @Override
            public List<SprintMeetingDto> loadList() {
                List<SprintMeeting> meetings = sprintRepository.findMeetings(map);
                return SprintMeetingDto.toDtos(meetings);
            }

            @Override
            public int loadTotalSize() {
                return sprintRepository.totalMeetings(map);
            }
        });
    }


}
