package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.domain.dto.log.LogDto;
import com.andaily.domain.dto.log.SprintActivityOverviewDto;
import com.andaily.domain.log.LogRepository;
import com.andaily.domain.log.SprintActivityLog;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.web.context.BeanProvider;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SprintActivityOverviewDtoLoader {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient LogRepository logRepository = BeanProvider.getBean(LogRepository.class);
    private SprintActivityOverviewDto activityOverviewDto;

    public SprintActivityOverviewDtoLoader(SprintActivityOverviewDto activityOverviewDto) {
        this.activityOverviewDto = activityOverviewDto;
    }

    public SprintActivityOverviewDto load() {
        final Sprint sprint = sprintRepository.findByGuid(activityOverviewDto.getSprintGuid());
        activityOverviewDto.setSprintDto(new SprintSimpleDto(sprint));

        final Map<String, Object> map = activityOverviewDto.defaultQueryMap();
        return activityOverviewDto.load(new PaginatedLoader<LogDto>() {
            @Override
            public List<LogDto> loadList() {
                List<SprintActivityLog> logs = logRepository.findSprintActivityLogs(map);
                return LogDto.toSprintActivityDtos(logs);
            }

            @Override
            public int loadTotalSize() {
                return logRepository.totalSprintActivityLogs(map);
            }
        });
    }
}