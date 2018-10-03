package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * Date: 13-10-21
 *
 * @author Shengzhao Li
 */
public class ProjectFormDtoLoader {

    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private String guid;

    public ProjectFormDtoLoader(String guid) {
        this.guid = guid;
    }

    public ProjectFormDto load() {
        ProjectFormDto projectFormDto = new ProjectFormDto(guid);
        if (!projectFormDto.isNewly()) {
            projectFormDto = loadExistDto(projectFormDto);
        }
        loadAvailableProductOwners(projectFormDto);
        return projectFormDto;
    }

    private void loadAvailableProductOwners(ProjectFormDto projectFormDto) {
        final User user = SecurityUtils.currUser();
        if (user.scrumTerm().equals(ScrumTerm.PRODUCT_OWNER)) {
            projectFormDto.getAvailableProductOwners().add(new DeveloperDto((Developer) user));
        } else {
            List<Developer> productOwners = developerRepository.findAllProductOwners();
            projectFormDto.setAvailableProductOwners(DeveloperDto.toDeveloperDtos(productOwners));
        }
    }

    private ProjectFormDto loadExistDto(ProjectFormDto projectFormDto) {
        Project project = projectRepository.findByGuid(projectFormDto.getGuid());
        projectFormDto = new ProjectFormDto(project);
        return projectFormDto;
    }
}
