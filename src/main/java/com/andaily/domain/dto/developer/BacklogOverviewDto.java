package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.BacklogType;
import com.andaily.domain.developer.SprintPriority;
import com.andaily.domain.shared.paginated.DefaultPaginated;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Date: 13-9-8
 *
 * @author Shengzhao Li
 */
public class BacklogOverviewDto extends DefaultPaginated<BacklogDto> {

    private SprintPriority priority;
    private BacklogType type;
    private String projectGuid;

    private String sprintGuid;
    private SprintDto sprintDto;
    private String number;

    public BacklogOverviewDto() {
    }

    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.defaultQueryMap();
        map.put("priority", priority);
        map.put("type", type);
        map.put("number", StringUtils.isEmpty(number) ? null : number);

        map.put("sprintGuid", StringUtils.isEmpty(sprintGuid) ? null : sprintGuid);
        map.put("projectGuid", (isSpecifyProject() ? projectGuid : null));
        if (isLimitProjects()) {
            final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
            map.put("isProductOwner", scrumTerm.equals(ScrumTerm.PRODUCT_OWNER) ? "yes" : null);
            map.put("isTeamMember", (scrumTerm.isMaster() || scrumTerm.isMember()) ? "yes" : null);
        }
        return map;
    }


    //If current user is not SUPER_MAN, then limit projects
    public boolean isLimitProjects() {
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        return !ScrumTerm.SUPER_MAN.equals(scrumTerm);
    }

    public boolean isSpecifyProject() {
        return StringUtils.isNotEmpty(projectGuid);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public void setSprintGuid(String sprintGuid) {
        this.sprintGuid = sprintGuid;
    }

    public SprintDto getSprintDto() {
        return sprintDto;
    }

    public BacklogOverviewDto setSprintDto(SprintDto sprintDto) {
        this.sprintDto = sprintDto;
        return this;
    }

    public BacklogType[] getAvailableTypes() {
        return BacklogType.values();
    }

    public SprintPriority[] getAvailablePriorities() {
        return SprintPriority.values();
    }

    public SprintPriority getPriority() {
        return priority;
    }

    public void setPriority(SprintPriority priority) {
        this.priority = priority;
    }

    public BacklogType getType() {
        return type;
    }

    public void setType(BacklogType type) {
        this.type = type;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }
}
