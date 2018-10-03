package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-8-3
 * <p/>
 * Define scrum sprint
 *
 * @author Shengzhao Li
 */
public class Sprint extends AbstractDomain {

    private static LogHelper log = LogHelper.create(Sprint.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private User creator;

    private String name;

    private Date startDate;
    //end date
    private Date deadline;
    //Default is Created
    private SprintStatus status = SprintStatus.CREATED;

    private Date pendingTime;
    private Date finishTime;

    private Date archiveTime;
    private User archiveUser;

    private List<Backlog> backlogs = new ArrayList<>();

    private List<SprintTask> tasks = new ArrayList<>();

    private List<SprintMeeting> meetings = new ArrayList<>();

    private String description;

    private Project project;

    //Only one default sprint in a project
    private boolean defaultSprint = false;

    //The team which do the sprint
    private Team executeTeam;

    public Sprint() {
    }

    public Sprint(String name, Date startDate, Date deadline) {
        this.name = name;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public Sprint updateCreator(User creator) {
        this.creator = creator;
        return this;
    }

    /**
     * The sprint estimate times (unit: minute)
     *
     * @return Times
     */
    public int estimateTimes() {
        int total = 0;
        for (SprintTask task : tasks) {
            if (!task.status().isCanceled()) {
                total += task.estimateTime();
            }
        }
        return total;
    }

    /**
     * The sprint already used times (unit: minute)
     *
     * @return Used times
     */
    public int usedTimes() {
        int used = 0;
        for (SprintTask task : tasks) {
            if (!task.status().isCanceled()) {
                used += task.actualUsedTime();
            }
        }
        return used;
    }

    public User creator() {
        return creator;
    }

    public String name() {
        return name;
    }

    public Date startDate() {
        return startDate;
    }

    public Date deadline() {
        return deadline;
    }

    public SprintStatus status() {
        return status;
    }

    public Date pendingTime() {
        return pendingTime;
    }

    public Date finishTime() {
        return finishTime;
    }

    public Date archiveTime() {
        return archiveTime;
    }

    public User archiveUser() {
        return archiveUser;
    }

    public List<Backlog> backlogs() {
        return backlogs;
    }

    public List<SprintTask> tasks() {
        return tasks;
    }

    public Sprint updateStatus(SprintStatus status) {
        this.status = status;
        return this;
    }

    public Sprint updateName(String name) {
        this.name = name;
        return this;
    }

    public String description() {
        return description;
    }

    public Sprint updateDescription(String description) {
        this.description = description;
        return this;
    }

    public Sprint updateStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Sprint updateDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            sprintRepository.saveSprint(this);
        } else {
            sprintRepository.updateSprint(this);
        }
    }

    public Sprint start() {
        ScrumSecurityChecker.checkSprint(this, SprintStatus.CREATED);
        this.status = SprintStatus.PENDING;
        this.pendingTime = DateUtils.now();
        log.debug(SecurityUtils.currUser().displayName() + " change the Sprint [" + this.name + "] status to [PENDING]");
        LogHandler.createSprintActivityLog("change the sprint [" + this.name + "] to [PENDING]", this);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", guid=" + guid +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Sprint archiveMe() {
        ScrumSecurityChecker.checkSprint(this, SprintStatus.CREATED);
        this.archived = true;
        this.archiveTime = DateUtils.now();
        this.archiveUser = SecurityUtils.currUser();

        log.debug(SecurityUtils.currUser().displayName() + " archive the Sprint [" + this.name + "]");
        LogHandler.createSprintActivityLog("archive the sprint [" + this.name + "]", this);
        this.saveOrUpdate();
        return this;
    }

    public Sprint finish() {
        ScrumSecurityChecker.checkSprint(this, SprintStatus.PENDING);
        if (allowFinish()) {
            this.status = SprintStatus.FINISHED;
            this.finishTime = DateUtils.now();
            log.debug(SecurityUtils.currUser().displayName() + " change the Sprint [" + this.name + "] status to [FINISHED]");
            LogHandler.createSprintActivityLog("change the sprint [" + this.name + "] to [FINISHED]", this);
        }
        return this;
    }

    /**
     * Check the sprint allow finish or not.
     * Allow conditions:
     * 1. Status: PENDING;
     * 2. All sprint tasks already finished(exclude canceled tasks)
     *
     * @return True is allow finish,otherwise false
     */
    public boolean allowFinish() {
        if (!this.status.equals(SprintStatus.PENDING)) {
            return false;
        }
        final int finishedTasks = amountOfTasksByStatus(SprintTaskStatus.FINISHED);
        final int canceledTasks = amountOfTasksByStatus(SprintTaskStatus.CANCELED);

        return (finishedTasks + canceledTasks) == tasks.size();
    }


    public int amountOfTasksByStatus(SprintTaskStatus taskStatus) {
        int amount = 0;
        for (SprintTask task : tasks) {
            if (task.status().equals(taskStatus)) {
                amount++;
            }
        }
        return amount;
    }

    public Project project() {
        return project;
    }

    public Sprint updateProject(Project project) {
        this.project = project;
        return this;
    }

    public List<SprintMeeting> meetings() {
        return meetings;
    }

    public boolean defaultSprint() {
        return defaultSprint;
    }

    public Sprint defaultSprint(boolean defaultSprint) {
        this.defaultSprint = defaultSprint;
        return this;
    }

    public Sprint markDefault() {
        sprintRepository.updateProjectSprintsNoDefault(this.project);
        this.defaultSprint(true).saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " mark the Sprint [" + this.name + "] is Default-Sprint in the project [" + this.project.name() + "]");
        return this;
    }

    public Team executeTeam() {
        return executeTeam;
    }

    public Sprint executeTeam(Team executeTeam) {
        this.executeTeam = executeTeam;
        return this;
    }

    public String cancelDefaultSprint() {
        if (!this.defaultSprint) {
            throw new IllegalStateException("The sprint is not default sprint,do not use cancel it");
        }
        this.defaultSprint(false).saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " cancel-default sprint [" + this.name + "] in the project [" + this.project.name() + "]");
        return project.guid();
    }


    /**
     * Get the current deadline , it is different {@link #deadline}
     * If the sprint is pending or finished, the deadline is from the last finished task time (if have)
     */
    public Date currentDeadline() {
        if (status.isCreated()) {
            return deadline;
        }
        final Date lastFinishedTime = sprintRepository.lastFinishedTaskTimeBySprint(this);
        return lastFinishedTime == null ? deadline : lastFinishedTime;
    }

}
