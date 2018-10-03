package com.andaily.service;

import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.domain.dto.developer.project.ProjectOverviewDto;

/**
 * Date: 13-10-19
 *
 * @author Shengzhao Li
 */
public interface ProjectService {

    ProjectOverviewDto loadProjectOverviewDto(ProjectOverviewDto projectOverviewDto);

    ProjectFormDto loadProjectFormDto(String guid);

    void persistProjectFormDto(ProjectFormDto projectFormDto);

    void archiveProject(String guid);
}
