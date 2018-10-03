package com.andaily.domain.dto.team;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamProject;
import com.andaily.domain.user.Developer;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-11-24
 *
 * @author Shengzhao Li
 */
public class TeamDto extends AbstractDTO {

    protected boolean archived;

    protected String name;
    protected String createDate;

    protected String creatorName;
    protected int projectSize;
    protected int memberSize;

    protected List<DeveloperDto> teamMembers = new ArrayList<>();
    protected List<ProjectDto> projectDtos = new ArrayList<>();

    protected String description;

    public TeamDto() {
    }

    public TeamDto(Team team) {
        this(team, true);
    }

    public TeamDto(Team team, boolean full) {
        super(team.guid());
        this.name = team.name();
        this.archived = team.archived();

        this.createDate = DateUtils.toDateText(team.createTime());
        this.creatorName = (team.creator() == null ? null : team.creator().displayName());
        this.description = team.description();

        if (full) {
            final List<TeamProject> teamProjects = team.teamProjects();
            this.projectSize = teamProjects.size();
            for (TeamProject teamProject : teamProjects) {
                this.projectDtos.add(new ProjectDto(teamProject.project()));
            }

            final List<Developer> members = team.members();
            this.memberSize = members.size();
            this.teamMembers = DeveloperDto.toDeveloperDtos(members);
        }
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlTip() {
        if (isNewly()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='text-info'>").append(description).append("</div><br/>");
        return StringEscapeUtils.escapeHtml(sb.toString());
    }

    public String getMembersText() {
        StringBuilder sb = new StringBuilder();
        for (DeveloperDto teamMember : teamMembers) {
            sb.append(teamMember.getShowName());
            if (teamMember.getScrumTerm().isMaster()) {
                sb.append("(Master)");
            }
            sb.append(", ");
        }
        final int length = sb.length();
        return length > 2 ? sb.toString().substring(0, length - 2) : sb.toString();
    }

    public String getProjectsText() {
        StringBuilder sb = new StringBuilder();
        for (ProjectDto projectDto : projectDtos) {
            sb.append(projectDto.getName()).append(", ");
        }
        if (projectDtos.isEmpty()) {
            sb.append(".... ");
        }
        final int length = sb.length();
        return length > 2 ? sb.toString().substring(0, length - 2) : sb.toString();
    }

    public List<DeveloperDto> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<DeveloperDto> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<ProjectDto> getProjectDtos() {
        return projectDtos;
    }

    public void setProjectDtos(List<ProjectDto> projectDtos) {
        this.projectDtos = projectDtos;
    }

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }

    public int getMemberSize() {
        return memberSize;
    }

    public void setMemberSize(int memberSize) {
        this.memberSize = memberSize;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<TeamDto> toDtos(List<Team> teams) {
        return toDtos(teams, true);
    }

    public static List<TeamDto> toDtos(List<Team> teams, boolean full) {
        List<TeamDto> dtos = new ArrayList<>(teams.size());
        for (Team team : teams) {
            dtos.add(new TeamDto(team, full));
        }
        return dtos;
    }
}
