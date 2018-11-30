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

    protected String startDate;
    
    protected String status;
    
    
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
        
        this.status = project.status();
        
        
        Date date = project.finishDate();
        if (date != null) {
            this.finishDate = DateUtils.toDateText(date);
        }
        
        
        Date date2 = project.startDate();
        if (date2 != null) {
            this.startDate = DateUtils.toDateText(date2);
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
    
    

    
    
    public String getStatus() {
        return status;
        
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    public String getStartDate() {
        return startDate;
    }

    public String getFinishDateAsText() {
        if (StringUtils.isEmpty(finishDate)) {
            return MessageUtils.getMessage(END_DATE_UNLIMITED_CODE);
        }
        return finishDate;
    }
    public String getStartDateAsText() {
        if (StringUtils.isEmpty(startDate)) {
            return MessageUtils.getMessage(END_DATE_UNLIMITED_CODE);
        }
        return startDate;
    }

    
    
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
