package com.andaily.service.impl;

import com.andaily.domain.developer.loader.ProjectFormDtoLoader;
import com.andaily.domain.developer.operation.ProjectFormDtoPersister;
import com.andaily.domain.developer.operation.ProjectOverviewDtoLoader;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.domain.dto.developer.project.ProjectOverviewDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-10-19
 *
 * @author Shengzhao Li
 */
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectOverviewDto loadProjectOverviewDto(ProjectOverviewDto projectOverviewDto) {
        ProjectOverviewDtoLoader loader = new ProjectOverviewDtoLoader(projectOverviewDto);
        return loader.load();
    }

    @Override
    public ProjectFormDto loadProjectFormDto(String guid) {
        ProjectFormDtoLoader loader = new ProjectFormDtoLoader(guid);
        return loader.load();
    }

    @Override
    public void persistProjectFormDto(ProjectFormDto projectFormDto) {
        ProjectFormDtoPersister persister = new ProjectFormDtoPersister(projectFormDto);
        persister.persist();
    }

    @Override
    public void archiveProject(String guid) {
        projectRepository.archiveProject(guid, true);
    }
}
