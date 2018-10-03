package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.SprintDto;
import com.andaily.domain.dto.developer.SprintOverviewDto;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SprintOverviewDtoLoader {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private SprintOverviewDto sprintOverviewDto;

    public SprintOverviewDtoLoader(SprintOverviewDto sprintOverviewDto) {
        this.sprintOverviewDto = sprintOverviewDto;
    }

    public SprintOverviewDto load() {
        final Map<String, Object> map = sprintOverviewDto.queryMap();
        sprintOverviewDto.load(new PaginatedLoader<SprintDto>() {
            @Override
            public List<SprintDto> loadList() {
                List<Sprint> sprints = sprintRepository.findOverviewSprints(map);
                return SprintDto.toDtos(sprints);
            }

            @Override
            public int loadTotalSize() {
                return sprintRepository.totalOverviewSprints(map);
            }
        });

        countSprints(map);
        loadProjectDto();
        return sprintOverviewDto;
    }

    private void loadProjectDto() {
        final String projectGuid = sprintOverviewDto.getProjectGuid();
        if (StringUtils.isNotEmpty(projectGuid)) {
            final Project project = projectRepository.findByGuid(projectGuid);
            sprintOverviewDto.setProjectDto(new ProjectDto(project));
        }
    }

    private void countSprints(Map<String, Object> map) {
        final SprintStatus status = sprintOverviewDto.getStatus();
        if (status == null) {
            final String status1 = "status";
            map.put(status1, SprintStatus.CREATED);
            sprintOverviewDto.setCreatedSprints(sprintRepository.totalOverviewSprints(map));

            map.put(status1, SprintStatus.PENDING);
            sprintOverviewDto.setPendingSprints(sprintRepository.totalOverviewSprints(map));

            map.put(status1, SprintStatus.FINISHED);
            sprintOverviewDto.setFinishedSprints(sprintRepository.totalOverviewSprints(map));
        }
    }
}