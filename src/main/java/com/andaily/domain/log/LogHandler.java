package com.andaily.domain.log;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import org.springframework.util.Assert;

/**
 * @author Shengzhao Li
 */
public abstract class LogHandler {


    public static void createSprintActivityLog(String content, Sprint sprint) {
        Assert.notNull(content, "Content is required");
        Assert.notNull(sprint, "Sprint is required");

        SprintActivityLog activityLog = new SprintActivityLog()
                .sprint(sprint)
                .operator((Developer) SecurityUtils.currUser())
                .content(content);
        activityLog.saveOrUpdate();
    }

    public static void createSprintActivityLog(String content, SprintTask task) {
        Assert.notNull(content, "Content is required");
        Assert.notNull(task, "SprintTask is required");

        SprintActivityLog activityLog = new SprintActivityLog()
                .task(task)
                .sprint(task.sprint())
                .operator((Developer) SecurityUtils.currUser())
                .content(content);
        activityLog.saveOrUpdate();
    }

    public static void createSprintActivityLog(String content, SprintMeeting meeting) {
        Assert.notNull(content, "Content is required");
        Assert.notNull(meeting, "SprintMeeting is required");

        SprintActivityLog activityLog = new SprintActivityLog()
                .meeting(meeting)
                .sprint(meeting.sprint())
                .operator((Developer) SecurityUtils.currUser())
                .content(content);
        activityLog.saveOrUpdate();
    }


}