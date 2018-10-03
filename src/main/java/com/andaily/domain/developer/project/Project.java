package com.andaily.domain.developer.project;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.team.TeamProject;
import com.andaily.domain.user.User;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-10-16
 *
 * @author Shengzhao Li
 */
public class Project extends AbstractDomain {

    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private String name;
    private String code;
    private String description;

    private User creator;
    /**
     * Null is available. it's mean the project is unlimited
     */
    private Date finishDate;

    private List<Sprint> sprints = new ArrayList<>();

    private List<Backlog> backlogs = new ArrayList<>();

    //product owners
    private List<ProjectProductOwner> productOwners = new ArrayList<>();

    //development teams
    private List<TeamProject> teams = new ArrayList<>();

    public Project() {
    }

    public Project(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    public User creator() {
        return creator;
    }

    public List<Sprint> sprints() {
        return sprints;
    }

    public List<Backlog> backlogs() {
        return backlogs;
    }


    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            projectRepository.saveProject(this);
        } else {
            projectRepository.updateProject(this);
        }
    }

    public Date finishDate() {
        return finishDate;
    }

    public Project finishDate(Date finishDate) {
        this.finishDate = finishDate;
        return this;
    }

    /**
     * How many tasks the project have.
     *
     * @return Count of tasks
     */
    public int countOfTasks() {
        return projectRepository.totalTasksOfProject(this);
    }

    /**
     * Max tak number in the project currently
     *
     * @return Max task number, 0 if the project task is empty
     */
    public int maxTaskNumber() {
        return projectRepository.maxTaskNumberOfProject(this);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", guid='" + guid + '\'' +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Project name(String name) {
        this.name = name;
        return this;
    }

    public Project code(String code) {
        this.code = code;
        return this;
    }

    public Project description(String description) {
        this.description = description;
        return this;
    }

    public Project creator(User creator) {
        this.creator = creator;
        return this;
    }

    public List<ProjectProductOwner> productOwners() {
        return productOwners;
    }

    public List<TeamProject> teams() {
        return teams;
    }
}
