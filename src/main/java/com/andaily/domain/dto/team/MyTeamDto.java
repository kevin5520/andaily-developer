package com.andaily.domain.dto.team;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.user.DeveloperDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MyTeamDto extends AbstractDTO {

    private boolean referTeam = true;

    private String teamName;
    private String teamDescription;

    private List<DeveloperDto> members = new ArrayList<>();

    private List<ProjectDto> projects = new ArrayList<>();

    public MyTeamDto() {
    }

    public boolean isReferTeam() {
        return referTeam;
    }

    public void setReferTeam(boolean referTeam) {
        this.referTeam = referTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public MyTeamDto setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public List<DeveloperDto> getMembers() {
        return members;
    }

    public void setMembers(List<DeveloperDto> members) {
        this.members = members;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}