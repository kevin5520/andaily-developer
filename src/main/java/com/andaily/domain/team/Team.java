package com.andaily.domain.team;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-11-20
 *
 * @author Shengzhao Li
 */
public class Team extends AbstractDomain {


    private static LogHelper log = LogHelper.create(Team.class);

    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private String name;

    private User creator;

    private List<TeamProject> teamProjects = new ArrayList<>();

    /**
     * The members includes: Scrum master, scrum member and product owner
     */
    private List<Developer> members = new ArrayList<>();

    private String description;

    public Team() {
    }


    public Team(String name, User creator) {
        this();
        this.name = name;
        this.creator = creator;
    }

    public String name() {
        return name;
    }

    public User creator() {
        return creator;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public List<TeamProject> teamProjects() {
        return teamProjects;
    }

    public List<Developer> members() {
        return members;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            teamRepository.saveTeam(this);
        } else {
            teamRepository.updateTeam(this);
        }
    }

    public String description() {
        return description;
    }

    public Team description(String description) {
        this.description = description;
        return this;
    }

    public Team archiveMe() {
        this.archived = true;
        this.saveOrUpdate();
        //clean refer developers
        if (this.members.size() > 0) {
            developerRepository.cleanDevelopersTeam(this.members());
        }
        //delete reference projects
        if (teamProjects.size() > 0) {
            teamRepository.deleteTeamProjects(teamProjects);
        }
        log.debug(SecurityUtils.currUser().displayName() + " archive the Team [" + guid + "]");
        return this;
    }
}
