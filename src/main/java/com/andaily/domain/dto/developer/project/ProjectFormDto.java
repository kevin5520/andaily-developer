package com.andaily.domain.dto.developer.project;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectProductOwner;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.security.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-10-21
 *
 * @author Shengzhao Li
 */
public class ProjectFormDto extends ProjectDto {

    private List<DeveloperDto> availableProductOwners = new ArrayList<>();
    private List<String> productOwnerGuids = new ArrayList<>();

    public ProjectFormDto() {
    }

    public ProjectFormDto(String guid) {
        this.guid = guid;
    }

    public ProjectFormDto(Project project) {
        super(project);

        final List<ProjectProductOwner> projectProductOwners = project.productOwners();
        for (ProjectProductOwner projectProductOwner : projectProductOwners) {
            productOwnerGuids.add(projectProductOwner.productOwner().guid());
        }
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public Project toDomain() {
        return new Project(name, code, description)
                .creator(SecurityUtils.currUser());
    }

    public List<DeveloperDto> getAvailableProductOwners() {
        return availableProductOwners;
    }

    public void setAvailableProductOwners(List<DeveloperDto> availableProductOwners) {
        this.availableProductOwners = availableProductOwners;
    }

    public List<String> getProductOwnerGuids() {
        return productOwnerGuids;
    }

    public void setProductOwnerGuids(List<String> productOwnerGuids) {
        this.productOwnerGuids = productOwnerGuids;
    }
}
