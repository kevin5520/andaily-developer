package com.andaily.domain.dto.developer.project;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectProductOwner;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.dto.user.UserDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.team.TeamProject;
import com.andaily.infrastructure.support.MessageUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-10-19
 *
 * @author Shengzhao Li
 */
public class ProjectDto extends AbstractDTO {

    public static final String END_DATE_UNLIMITED_CODE = "project.end.date.unlimited";

    protected String createTime;

    protected String name;
    protected String code;
    protected String description;

    protected UserDto creator;
    protected String finishDate;

    protected int amountOfSprints;
    protected int amountOfBacklogs;

    protected List<DeveloperDto> productOwners = new ArrayList<>();

    protected List<TeamDto> teams = new ArrayList<>();

    public ProjectDto() {
    }

    public ProjectDto(Project project) {
        super(project.guid());
        this.createTime = DateUtils.toDateText(project.createTime(), DateUtils.DATE_TIME_FORMAT);

        this.name = project.name();
        this.code = project.code();
        this.description = project.description();

        Date date = project.finishDate();
        if (date != null) {
            this.finishDate = DateUtils.toDateText(date);
        }

        this.amountOfBacklogs = project.backlogs().size();
        this.amountOfSprints = project.sprints().size();

        final List<ProjectProductOwner> projectProductOwners = project.productOwners();
        for (ProjectProductOwner projectProductOwner : projectProductOwners) {
            this.productOwners.add(new DeveloperDto(projectProductOwner.productOwner()));
        }

        final List<TeamProject> teamProjects = project.teams();
        for (TeamProject teamProject : teamProjects) {
            this.teams.add(new TeamDto(teamProject.team()));
        }
    }

    public String getProductOwnersAsText() {
        StringBuilder sb = new StringBuilder();
        for (DeveloperDto productOwner : productOwners) {
            sb.append(productOwner.getShowName()).append(", ");
        }
        final int length = sb.length();
        return length > 1 ? sb.toString().substring(0, length - 2) : sb.toString();
    }

    public String getTeamsAsText() {
        StringBuilder sb = new StringBuilder();
        for (TeamDto teamDto : teams) {
            sb.append(teamDto.getName()).append(", ");
        }
        final int length = sb.length();
        return length > 1 ? sb.toString().substring(0, length - 2) : sb.toString();
    }

    public List<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDto> teams) {
        this.teams = teams;
    }

    public List<DeveloperDto> getProductOwners() {
        return productOwners;
    }

    public void setProductOwners(List<DeveloperDto> productOwners) {
        this.productOwners = productOwners;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getFinishDateAsText() {
        if (StringUtils.isEmpty(finishDate)) {
            return MessageUtils.getMessage(END_DATE_UNLIMITED_CODE);
        }
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public static List<ProjectDto> toDtos(List<Project> projects) {
        List<ProjectDto> dtoList = new ArrayList<ProjectDto>(projects.size());
        for (Project project : projects) {
            dtoList.add(new ProjectDto(project));
        }
        return dtoList;
    }

    public String getHtmlTip() {
        if (isNewly()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='text-info'>").append(description).append("</div><br/>");
        return StringEscapeUtils.escapeHtml(sb.toString());
    }

    public int getAmountOfSprints() {
        return amountOfSprints;
    }

    public void setAmountOfSprints(int amountOfSprints) {
        this.amountOfSprints = amountOfSprints;
    }

    public int getAmountOfBacklogs() {
        return amountOfBacklogs;
    }

    public void setAmountOfBacklogs(int amountOfBacklogs) {
        this.amountOfBacklogs = amountOfBacklogs;
    }
}
