package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.shared.paginated.DefaultPaginated;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Date: 13-8-30
 *
 * @author Shengzhao Li
 */
public class SprintOverviewDto extends DefaultPaginated<SprintDto> {

    private SprintStatus status;
    private String projectGuid;

    private int createdSprints;
    private int pendingSprints;
    private int finishedSprints;

    private ProjectDto projectDto;

    public SprintOverviewDto() {
    }

    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.defaultQueryMap();
        map.put("status", status);
        map.put("projectGuid", (isSpecifyProject() ? projectGuid : null));
        // Different role will call different limit
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        map.put("isProductOwner", scrumTerm.equals(ScrumTerm.PRODUCT_OWNER) ? "yes" : null);
        map.put("isTeamMember", (scrumTerm.isMaster() || scrumTerm.isMember()) ? "yes" : null);
        return map;
    }

    public boolean isSpecifyProject() {
        return StringUtils.isNotEmpty(projectGuid);
    }

    public SprintStatus[] getAvailableStatuses() {
        return SprintStatus.values();
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public SprintOverviewDto setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
        return this;
    }

    @Override
    public void afterLoad() {
        if (this.status != null) {
            switch (this.status) {
                case CREATED:
                    this.createdSprints = totalSize;
                    break;
                case PENDING:
                    this.pendingSprints = totalSize;
                    break;
                case FINISHED:
                    this.finishedSprints = totalSize;
                    break;
            }
        }
    }

    public int getCreatedSprints() {
        return createdSprints;
    }

    public SprintOverviewDto setCreatedSprints(int createdSprints) {
        this.createdSprints = createdSprints;
        return this;
    }

    public int getPendingSprints() {
        return pendingSprints;
    }

    public SprintOverviewDto setPendingSprints(int pendingSprints) {
        this.pendingSprints = pendingSprints;
        return this;
    }

    public int getFinishedSprints() {
        return finishedSprints;
    }

    public SprintOverviewDto setFinishedSprints(int finishedSprints) {
        this.finishedSprints = finishedSprints;
        return this;
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }
}
