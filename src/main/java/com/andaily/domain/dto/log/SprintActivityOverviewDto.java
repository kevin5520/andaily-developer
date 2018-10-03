package com.andaily.domain.dto.log;

import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.domain.shared.paginated.DefaultPaginated;

import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SprintActivityOverviewDto extends DefaultPaginated<LogDto> {

    private String sprintGuid;
    private SprintSimpleDto sprintDto;

    public SprintActivityOverviewDto() {
    }

    @Override
    public Map<String, Object> defaultQueryMap() {
        final Map<String, Object> map = super.defaultQueryMap();
        map.put("sprintGuid", sprintGuid);
        return map;
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public SprintActivityOverviewDto setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
        return this;
    }

    public SprintSimpleDto getSprintDto() {
        return sprintDto;
    }

    public SprintActivityOverviewDto setSprintDto(SprintSimpleDto sprintDto) {
        this.sprintDto = sprintDto;
        return this;
    }
}