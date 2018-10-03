package com.andaily.domain.log;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintTask;

/**
 * @author Shengzhao Li
 */
public class SprintActivityLog extends Log {


    private Sprint sprint;
    //null is available
    private SprintTask task;
    //null is available
    private SprintMeeting meeting;


    public SprintActivityLog() {
    }

    public Sprint sprint() {
        return sprint;
    }

    public SprintActivityLog sprint(Sprint sprint) {
        this.sprint = sprint;
        return this;
    }

    public SprintTask task() {
        return task;
    }

    public SprintActivityLog task(SprintTask task) {
        this.task = task;
        return this;
    }

    public SprintMeeting meeting() {
        return meeting;
    }

    public SprintActivityLog meeting(SprintMeeting meeting) {
        this.meeting = meeting;
        return this;
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            logRepository.saveSprintActivityLog(this);
        } else {
            throw new UnsupportedOperationException("Update SprintActivityLog is unsupported");
        }
    }
}