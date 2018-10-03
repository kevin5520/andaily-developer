package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.dto.developer.BacklogDto;
import com.andaily.domain.dto.developer.BacklogOverviewDto;
import com.andaily.domain.dto.developer.SprintDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class BacklogOverviewDtoLoader {

    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private BacklogOverviewDto backlogOverviewDto;

    public BacklogOverviewDtoLoader(BacklogOverviewDto backlogOverviewDto) {
        this.backlogOverviewDto = backlogOverviewDto;
    }

    public BacklogOverviewDto load() {
        loadSprintDto();

        final Map<String, Object> map = backlogOverviewDto.queryMap();
        return backlogOverviewDto.load(new PaginatedLoader<BacklogDto>() {
            @Override
            public List<BacklogDto> loadList() {
                List<Backlog> backlogs = backlogRepository.findOverviewBacklogs(map);
                return BacklogDto.toDtos(backlogs);
            }

            @Override
            public int loadTotalSize() {
                return backlogRepository.totalOverviewBacklogs(map);
            }
        });
    }

    private void loadSprintDto() {
        final String sprintGuid = backlogOverviewDto.getSprintGuid();
        if (StringUtils.isNotEmpty(sprintGuid)) {
            final Sprint sprint = sprintRepository.findByGuid(sprintGuid);
            backlogOverviewDto.setSprintDto(new SprintDto(sprint));
        }
    }
}