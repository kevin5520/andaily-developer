package com.andaily.domain.team;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.developer.project.Project;

/**
 * Date: 13-11-20
 *
 * @author Shengzhao Li
 */
public class TeamProject extends AbstractDomain {

    private Team team;
    private Project project;

    public TeamProject() {
    }

    public TeamProject(Team team, Project project) {
        this();
        this.team = team;
        this.project = project;
    }

    public Team team() {
        return team;
    }

    public Project project() {
        return project;
    }
}
