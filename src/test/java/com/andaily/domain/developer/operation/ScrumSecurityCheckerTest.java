package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.SprintTaskStatus;
import org.testng.annotations.Test;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
public class ScrumSecurityCheckerTest {

    @Test
    public void testCheckSprintTask() throws Exception {
        SprintTask task = new SprintTask();
        ScrumSecurityChecker.checkSprintTask(task, SprintTaskStatus.PENDING, SprintTaskStatus.CREATED);
    }
}
