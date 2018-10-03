package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.*;

import java.util.Arrays;

/**
 * Date: 13-8-18
 * <p/>
 * Check the action is available or unavailable;
 * if it is unavailable, throw IllegalStatusException.
 *
 * @author Shengzhao Li
 */
public abstract class ScrumSecurityChecker {

    /**
     * private
     */
    private ScrumSecurityChecker() {
    }

    public static void checkSprintTask(SprintTask task, SprintTaskStatus... expectedStatuses) {
        SprintTaskStatus status = task.status();
        boolean success = false;
        for (SprintTaskStatus expectedStatus : expectedStatuses) {
            if (status.equals(expectedStatus)) {
                success = true;
            }
        }
        if (!success) {
            throw new SecurityException("The SprintTask expects statuses {" + Arrays.toString(expectedStatuses)
                    + "}, but it is {" + status + "}");
        }
    }

    public static void checkSprint(Sprint sprint, SprintStatus... expectedStatuses) {
        SprintStatus status = sprint.status();
        boolean success = false;
        for (SprintStatus expectedStatus : expectedStatuses) {
            if (status.equals(expectedStatus)) {
                success = true;
            }
        }
        if (!success) {
            throw new SecurityException("The Sprint expects statuses {" + Arrays.toString(expectedStatuses)
                    + "}, but it is {" + status + "}");
        }
    }

    public static void checkBacklogReferSprint(Backlog backlog, boolean refer) {
        Sprint sprint = backlog.sprint();
        if (refer && sprint == null) {
            throw new SecurityException("Expect the backlog{" + backlog.id() + "} refer a Sprint, but it is null");
        }
        if (!refer && sprint != null) {
            throw new SecurityException("Expect the backlog{" + backlog.id() + "} not refer a Sprint, but the sprint is " + sprint);
        }
    }
}
