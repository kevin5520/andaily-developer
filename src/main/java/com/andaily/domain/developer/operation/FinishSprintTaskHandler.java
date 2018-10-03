package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.dto.developer.FinishSprintTaskDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.User;
import com.andaily.web.context.BeanProvider;

/**
 * Date: 13-11-12
 *
 * @author Shengzhao Li
 */
public class FinishSprintTaskHandler {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private FinishSprintTaskDto finishSprintTaskDto;

    public FinishSprintTaskHandler(FinishSprintTaskDto finishSprintTaskDto) {
        this.finishSprintTaskDto = finishSprintTaskDto;
    }

    public String handle() {
        SprintTask task = sprintRepository.findTaskByGuid(finishSprintTaskDto.getGuid());
        checkingExecutor(task);

        task.finish(finishSprintTaskDto).saveOrUpdate();
        return task.sprint().guid();
    }

    private void checkingExecutor(SprintTask task) {
        // checking executor is current login user or not
        final User user = SecurityUtils.currUser();
        if (!task.executor().equals(user)) {
            throw new IllegalStateException("You can't finish the task, because the task executor is [" + user.displayName() + "]");
        }
    }
}
