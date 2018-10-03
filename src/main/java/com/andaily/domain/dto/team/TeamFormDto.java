package com.andaily.domain.dto.team;

import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.grouper.GroupResult;
import com.andaily.domain.shared.grouper.impl.DeveloperDtoScrumTermGrouper;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-12-18
 *
 * @author Shengzhao Li
 */
public class TeamFormDto extends TeamDto {


    private List<DeveloperDto> developerDtoList = new ArrayList<DeveloperDto>();
    private List<SimpleProjectData> projects = new ArrayList<>();

    private List<String> masters = new ArrayList<String>();
    private List<String> members = new ArrayList<String>();

    private List<String> projectGuids = new ArrayList<>();

    public TeamFormDto() {
    }

    public TeamFormDto(String guid) {
        this.guid = guid;
    }

    public TeamFormDto(Team team) {
        super(team);
    }

    public List<GroupResult<ScrumTerm, DeveloperDto>> getDeveloperDtoGrouperList() {
        DeveloperDtoScrumTermGrouper grouper = new DeveloperDtoScrumTermGrouper(developerDtoList);
        return grouper.group().getGroupResults();
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public List<String> getMasters() {
        return masters;
    }

    public void setMasters(List<String> masters) {
        this.masters = masters;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<DeveloperDto> getDeveloperDtoList() {
        return developerDtoList;
    }

    public void setDeveloperDtoList(List<DeveloperDto> developerDtoList) {
        this.developerDtoList = developerDtoList;
    }

    public List<SimpleProjectData> getProjects() {
        return projects;
    }

    public TeamFormDto setProjects(List<SimpleProjectData> projects) {
        this.projects = projects;
        return this;
    }

    public List<String> getProjectGuids() {
        return projectGuids;
    }

    public void setProjectGuids(List<String> projectGuids) {
        this.projectGuids = projectGuids;
    }

    public Team toDomain() {
        return new Team(name, SecurityUtils.currUser())
                .description(description);
    }
}
