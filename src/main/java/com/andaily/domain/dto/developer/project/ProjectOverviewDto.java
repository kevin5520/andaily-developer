package com.andaily.domain.dto.developer.project;

import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.paginated.DefaultPaginated;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-10-19
 *
 * @author Shengzhao Li
 */
public class ProjectOverviewDto extends DefaultPaginated<ProjectDto> {

    private String name;
    private String teamGuid;
    private String productOwnerGuid;

    private List<TeamDto> teamDtos = new ArrayList<>();
    private List<DeveloperDto> productOwners = new ArrayList<>();

    public ProjectOverviewDto() {
    }

    public Map<String, Object> queryMap() {
        Map<String, Object> map = super.defaultQueryMap();
        map.put("name", StringUtils.isNotEmpty(name) ? "%" + name + "%" : null);

        final User currUser = SecurityUtils.currUser();
        if (isShowTeamCondition()) {
            map.put("teamGuid", StringUtils.isNotEmpty(teamGuid) ? teamGuid : null);
        } else {
            map.put("teamGuid", currUser.team().guid());
        }

        if (isShowProductOwnerCondition()) {
            map.put("productOwnerGuid", StringUtils.isNotEmpty(productOwnerGuid) ? productOwnerGuid : null);
        } else {
            map.put("productOwnerGuid", currUser.scrumTerm().equals(ScrumTerm.PRODUCT_OWNER) ? currUser.guid() : null);
        }
        return map;
    }

    public boolean isShowTeamCondition() {
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        return ScrumTerm.PRODUCT_OWNER.equals(scrumTerm) || ScrumTerm.SUPER_MAN.equals(scrumTerm);
    }

    public boolean isShowProductOwnerCondition() {
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        return ScrumTerm.SUPER_MAN.equals(scrumTerm);
    }

    public String getTeamGuid() {
        return teamGuid;
    }

    public void setTeamGuid(String teamGuid) {
        this.teamGuid = teamGuid;
    }

    public List<TeamDto> getTeamDtos() {
        return teamDtos;
    }

    public void setTeamDtos(List<TeamDto> teamDtos) {
        this.teamDtos = teamDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductOwnerGuid() {
        return productOwnerGuid;
    }

    public void setProductOwnerGuid(String productOwnerGuid) {
        this.productOwnerGuid = productOwnerGuid;
    }

    public List<DeveloperDto> getProductOwners() {
        return productOwners;
    }

    public void setProductOwners(List<DeveloperDto> productOwners) {
        this.productOwners = productOwners;
    }
}
