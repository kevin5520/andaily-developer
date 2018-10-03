package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-8-3
 *
 * @author Shengzhao Li
 */
public class Backlog extends AbstractDomain {

    private static LogHelper log = LogHelper.create(Backlog.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private SprintPriority priority = SprintPriority.DEFAULT;

    private String content;

    //backlog number, unique
    private int number;

    private BacklogType type = BacklogType.NEEDS;

    //If the backlog do not refer an exist sprint, the value is null
    private Sprint sprint;

    private User creator;

    // update the field value when create Sprint
    private User joinSprintUser;
    private Date joinSprintTime;

    // The field max hours is 16
    //Unit: hour
    private int estimateTime;

    private Project project;

    //documents
    private List<BacklogDocument> documents = new ArrayList<>();

    public Backlog() {
    }

    public Backlog(SprintPriority priority, String content, BacklogType type) {
        this.priority = priority;
        this.content = content;
        this.type = type;
    }

    public int number() {
        return number;
    }

    public Backlog number(int number) {
        this.number = number;
        return this;
    }

    public Backlog updateCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public SprintPriority priority() {
        return priority;
    }

    public String content() {
        return content;
    }

    public BacklogType type() {
        return type;
    }

    public Sprint sprint() {
        return sprint;
    }

    public User creator() {
        return creator;
    }

    public User joinSprintUser() {
        return joinSprintUser;
    }

    public Date joinSprintTime() {
        return joinSprintTime;
    }

    public Backlog updateContent(String content) {
        this.content = content;
        return this;
    }

    public Backlog updateJoinSprintTime(Date joinSprintTime) {
        this.joinSprintTime = joinSprintTime;
        return this;
    }

    public Backlog updateSprint(Sprint sprint) {
        this.sprint = sprint;
        return this;
    }

    public Backlog updateType(BacklogType type) {
        this.type = type;
        return this;
    }

    public Backlog updatePriority(SprintPriority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            backlogRepository.saveBacklog(this);
        } else {
            backlogRepository.updateBacklog(this);
        }
    }

    public Backlog archive() {
        ScrumSecurityChecker.checkBacklogReferSprint(this, false);
        this.archived(true);
        log.debug(SecurityUtils.currUser().displayName() + " archive the backlog [" + this.guid + "]");
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id() + '\'' +
                ",content='" + content + '\'' +
                '}';
    }

    public int estimateTime() {
        return estimateTime;
    }

    public Backlog updateEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
        return this;
    }

    public Project project() {
        return project;
    }

    public Backlog updateProject(Project project) {
        this.project = project;
        return this;
    }

    public List<BacklogDocument> documents() {
        return documents;
    }

    /**
     * Amount of the task refer the backlog
     *
     * @return Amount of task
     */
    public int referencedSprintTasks() {
        return sprintRepository.totalSprintTasksOfBacklog(this);
    }
}
