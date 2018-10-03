package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.SprintTaskStatus;
import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.user.Developer;
import com.andaily.infrastructure.support.st.STRender;

import java.math.BigDecimal;
import java.util.*;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 * @see Sprint
 */
public class SprintDto extends AbstractDTO {

    protected String name;
    protected String startDate;

    protected String deadline;
    protected String currentDeadline;

    protected SprintStatus status = SprintStatus.CREATED;

    protected String pendingTime;
    protected String finishTime;
    protected String description;

    protected String archiveTime;
    protected int estimateTimes;
    protected int usedTimes;

    protected String createTime;
    protected int amountOfTask;
    protected int finishedTasks;

    protected int amountOfBacklog;

    protected ProjectDto projectDto;
    protected int amountOfMeeting;
    protected boolean defaultSprint;

    protected TeamDto executeTeamDto;
    protected DeveloperDto creator;

    public SprintDto() {
    }

    public SprintDto(Sprint sprint) {
        super(sprint.guid());
        this.name = sprint.name();
        this.status = sprint.status();
        this.estimateTimes = sprint.estimateTimes();

        this.usedTimes = sprint.usedTimes();
        this.description = sprint.description();
        this.amountOfTask = sprint.tasks().size();

        this.finishedTasks = sprint.amountOfTasksByStatus(SprintTaskStatus.FINISHED);
        this.amountOfBacklog = sprint.backlogs().size();

        this.createTime = DateUtils.toDateTime(sprint.createTime());
        this.amountOfMeeting = sprint.meetings().size();
        this.defaultSprint = sprint.defaultSprint();
        Date sDate = sprint.startDate();
        if (sDate != null) {
            this.startDate = DateUtils.toDateText(sDate);
        }
        Date eDate = sprint.deadline();
        if (eDate != null) {
            this.deadline = DateUtils.toDateText(eDate);
        }
        this.pendingTime = sprint.pendingTime() != null ? DateUtils.toDateTime(sprint.pendingTime()) : null;
        this.finishTime = sprint.finishTime() != null ? DateUtils.toDateTime(sprint.finishTime()) : null;
        this.archiveTime = sprint.archiveTime() != null ? DateUtils.toDateTime(sprint.archiveTime()) : null;

        this.projectDto = new ProjectDto(sprint.project());
        final Team team = sprint.executeTeam();
        if (team != null) {
            this.executeTeamDto = new TeamDto(team, false);
        }
        this.creator = new DeveloperDto((Developer) sprint.creator());

        final Date currDeadline = sprint.currentDeadline();
        if (currDeadline != null) {
            this.currentDeadline = DateUtils.toDateText(currDeadline);
        }
    }

    public int getFinishedTasks() {
        return finishedTasks;
    }

    public void setFinishedTasks(int finishedTasks) {
        this.finishedTasks = finishedTasks;
    }

    public String getCurrentDeadline() {
        return currentDeadline;
    }

    public void setCurrentDeadline(String currentDeadline) {
        this.currentDeadline = currentDeadline;
    }

    public DeveloperDto getCreator() {
        return creator;
    }

    public void setCreator(DeveloperDto creator) {
        this.creator = creator;
    }

    public TeamDto getExecuteTeamDto() {
        return executeTeamDto;
    }

    public void setExecuteTeamDto(TeamDto executeTeamDto) {
        this.executeTeamDto = executeTeamDto;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public String getHtmlTip() {
        if (isNewly()) {
            return "";
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("status", status.getLabel());
        model.put("estimateTimesAsHour", getEstimateTimesAsHour());
        model.put("usedTimesAsHour", getUsedTimesAsHour());

        model.put("timeDifferenceAsHtml", getTimeDifferenceAsHtml());
        model.put("createTime", createTime);
        model.put("creator", creator.getShowName());
        model.put("startDate", startDate);

        model.put("deadline", deadline);
        model.put("showCurrDeadline", isShowCurrDeadline());
        model.put("currDeadline", currentDeadline);

        model.put("description", description);
        model.put("team", this.executeTeamDto != null ? this.executeTeamDto.getName() : "");

        final boolean chinese = SecurityUtils.currUser().language().isChinese();
        final String filePath = chinese ? "template/sprint_tip_cn.html" : "template/sprint_tip.html";
        STRender stRender = new STRender(filePath, model);
        return stRender.render();
    }

    public boolean isShowCurrDeadline() {
        return currentDeadline != null && !currentDeadline.equals(deadline);
    }

    //Only use if the sprint status is FINISHED
    public String getTimeDifferenceAsHtml() {
        if (!SprintStatus.FINISHED.equals(this.status)) {
            return "";
        }
        StringBuilder text = new StringBuilder();
        int timeDifference = estimateTimes - usedTimes;
        if (timeDifference > 0) {
            text.append("(<span class='blue'>-").append(SprintTimeUtils.taskTimeAsString(timeDifference)).append("</span>)");
        } else if (timeDifference < 0) {
            text.append("(<span class='red'>+").append(SprintTimeUtils.taskTimeAsString(-timeDifference)).append("</span>)");
        }
        return text.toString();
    }

    /*
    *  Rule:   finished tasks/ total tasks    *  100%
    *
    * */
    public String getUsedTimeAsPercent() {
        if (amountOfTask == 0) {
            return "0%";
        }

        BigDecimal result = new BigDecimal(finishedTasks * 100).divide(new BigDecimal(amountOfTask), 2, BigDecimal.ROUND_HALF_EVEN);
        return result.toString() + "%";
    }

    public boolean isHaveBacklogs() {
        return amountOfBacklog > 0;
    }

    public boolean isFinished() {
        return status != null && status.isFinished();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getAmountOfBacklog() {
        return amountOfBacklog;
    }

    public void setAmountOfBacklog(int amountOfBacklog) {
        this.amountOfBacklog = amountOfBacklog;
    }

    public int getEstimateTimes() {
        return estimateTimes;
    }

    public String getEstimateTimesAsHour() {
        return SprintTimeUtils.taskTimeAsString(estimateTimes);
    }

    public void setEstimateTimes(int estimateTimes) {
        this.estimateTimes = estimateTimes;
    }

    public int getUsedTimes() {
        return usedTimes;
    }

    public String getUsedTimesAsHour() {
        return SprintTimeUtils.taskTimeAsString(usedTimes);
    }

    public void setUsedTimes(int usedTimes) {
        this.usedTimes = usedTimes;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public String getPendingTime() {
        return pendingTime;
    }

    public void setPendingTime(String pendingTime) {
        this.pendingTime = pendingTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(String archiveTime) {
        this.archiveTime = archiveTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountOfTask() {
        return amountOfTask;
    }

    public void setAmountOfTask(int amountOfTask) {
        this.amountOfTask = amountOfTask;
    }

    public static List<SprintDto> toDtos(List<Sprint> sprints) {
        List<SprintDto> sprintDtos = new ArrayList<>(sprints.size());
        for (Sprint sprint : sprints) {
            sprintDtos.add(new SprintDto(sprint));
        }
        return sprintDtos;
    }

    public int getAmountOfMeeting() {
        return amountOfMeeting;
    }

    public void setAmountOfMeeting(int amountOfMeeting) {
        this.amountOfMeeting = amountOfMeeting;
    }

    public boolean isDefaultSprint() {
        return defaultSprint;
    }

    public void setDefaultSprint(boolean defaultSprint) {
        this.defaultSprint = defaultSprint;
    }
}
