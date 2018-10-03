package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-8-14
 *
 * @author Shengzhao Li
 */
public class SprintFormDto extends SprintDto {

    private boolean addTask;
    private String today = DateUtils.toDateText(DateUtils.now());

    private List<SprintFormBacklogDto> backlogs = new ArrayList<SprintFormBacklogDto>();
    private String existName;
    private int budgetBacklogsTime;

    private String projectGuid;
    private List<SimpleProjectData> availableProjects = new ArrayList<>();

    private String teamGuid;
    private List<TeamDto> availableTeams = new ArrayList<>();

    public SprintFormDto() {
    }

    public SprintFormDto(Sprint sprint) {
        super(sprint);
        this.existName = sprint.name();
        this.projectGuid = sprint.project().guid();
        this.teamGuid = sprint.executeTeam().guid();
    }

    public SprintFormDto(String guid, String projectGuid) {
        this.guid = guid;
        this.projectGuid = projectGuid;
    }

    public String getTeamGuid() {
        return teamGuid;
    }

    public void setTeamGuid(String teamGuid) {
        this.teamGuid = teamGuid;
    }

    public List<TeamDto> getAvailableTeams() {
        return availableTeams;
    }

    public void setAvailableTeams(List<TeamDto> availableTeams) {
        this.availableTeams = availableTeams;
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public boolean isAddTask() {
        return addTask;
    }

    public void setAddTask(boolean addTask) {
        this.addTask = addTask;
    }

    public SprintFormDto initialStartDate() {
        setStartDate(today);
        return this;
    }

    public String getToday() {
        return today;
    }

    public List<SprintFormBacklogDto> getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(List<SprintFormBacklogDto> backlogs) {
        this.backlogs = backlogs;
    }

    public String getExistName() {
        return existName;
    }

    public void setExistName(String existName) {
        this.existName = existName;
    }

    public int getBudgetBacklogsTime() {
        return budgetBacklogsTime;
    }

    public void setBudgetBacklogsTime(int budgetBacklogsTime) {
        this.budgetBacklogsTime = budgetBacklogsTime;
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public List<SimpleProjectData> getAvailableProjects() {
        return availableProjects;
    }

    public void setAvailableProjects(List<SimpleProjectData> availableProjects) {
        this.availableProjects = availableProjects;
    }
}
