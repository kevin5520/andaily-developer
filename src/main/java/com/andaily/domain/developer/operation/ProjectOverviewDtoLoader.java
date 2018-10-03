package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.developer.project.ProjectOverviewDto;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.web.context.BeanProvider;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class ProjectOverviewDtoLoader {

    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private ProjectOverviewDto projectOverviewDto;

    public ProjectOverviewDtoLoader(ProjectOverviewDto projectOverviewDto) {
        this.projectOverviewDto = projectOverviewDto;
    }

    public ProjectOverviewDto load() {
        loadTeams();
        loadProductOwners();

        final Map<String, Object> map = projectOverviewDto.queryMap();
        projectOverviewDto = projectOverviewDto.load(new PaginatedLoader<ProjectDto>() {
            @Override
            public List<ProjectDto> loadList() {
                List<Project> projects = projectRepository.findOverviewProjects(map);
                return ProjectDto.toDtos(projects);
            }

            @Override
            public int loadTotalSize() {
                return projectRepository.totalOverviewProjects(map);
            }
        });
        return projectOverviewDto;
    }

    private void loadProductOwners() {
        final List<Developer> allProductOwners = developerRepository.findAllProductOwners();
        projectOverviewDto.setProductOwners(DeveloperDto.toDeveloperDtos(allProductOwners));
    }

    private void loadTeams() {
        final List<Team> availableTeams = teamRepository.findAvailableTeams();
        this.projectOverviewDto.setTeamDtos(TeamDto.toDtos(availableTeams));
    }
}