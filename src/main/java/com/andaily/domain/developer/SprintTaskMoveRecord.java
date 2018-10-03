package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;

/**
 * Date: 13-10-9
 * <p/>
 * Record the sprint task move history.
 *
 * @author Shengzhao Li
 */
public class SprintTaskMoveRecord extends AbstractDomain {

    private Sprint fromSprint;
    private Sprint toSprint;

    private SprintTask task;

    public SprintTaskMoveRecord() {
    }


    public SprintTaskMoveRecord(Sprint fromSprint, Sprint toSprint, SprintTask task) {
        this.fromSprint = fromSprint;
        this.toSprint = toSprint;
        this.task = task;
    }

    public Sprint fromSprint() {
        return fromSprint;
    }

    public Sprint toSprint() {
        return toSprint;
    }

    public SprintTask task() {
        return task;
    }
}
