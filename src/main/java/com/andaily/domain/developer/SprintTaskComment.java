package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class SprintTaskComment extends AbstractDomain {

    private static LogHelper log = LogHelper.create(SprintTaskComment.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private SprintTask task;

    private String content;

    private Developer who;

    public SprintTaskComment() {
    }

    public SprintTaskComment(SprintTask task, String content) {
        this.task = task;
        this.content = content;
        this.who = (Developer) SecurityUtils.currUser();
    }


    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            sprintRepository.saveSprintTaskComment(this);
        } else {
            throw new UnsupportedOperationException("Update SprintTaskComment is unsupported");
        }
    }

    public SprintTask task() {
        return task;
    }

    public SprintTaskComment task(SprintTask task) {
        this.task = task;
        return this;
    }

    public String content() {
        return content;
    }

    public SprintTaskComment content(String content) {
        this.content = content;
        return this;
    }

    public Developer who() {
        return who;
    }

    public SprintTaskComment who(Developer who) {
        this.who = who;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{content='").append(content).append('\'');
        sb.append(",taskId='").append(task.id()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean deleteMe() {
        final User currUser = SecurityUtils.currUser();
        if (!currUser.equals(this.who)) {
            return false;
        }
        sprintRepository.deleteSprintTaskComment(this);
        log.debug(currUser.displayName() + " delete the comment " + this);
        LogHandler.createSprintActivityLog("delete the task [" + task.name() + "] comment [" + content + "]", this.task);
        return true;
    }
}