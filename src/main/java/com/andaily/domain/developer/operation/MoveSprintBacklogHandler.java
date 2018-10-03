package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.dto.developer.MoveSprintBacklogDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class MoveSprintBacklogHandler {

    private static LogHelper log = LogHelper.create(MoveSprintBacklogHandler.class);

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);

    private MoveSprintBacklogDto moveSprintBacklogDto;

    public MoveSprintBacklogHandler(MoveSprintBacklogDto moveSprintBacklogDto) {
        this.moveSprintBacklogDto = moveSprintBacklogDto;
    }

    public boolean handle() {
        final Backlog backlog = backlogRepository.findByGuid(moveSprintBacklogDto.getBacklogGuid());
        final Sprint sprint = sprintRepository.findByGuid(moveSprintBacklogDto.getSprintGuid());

        if (moveSprintBacklogDto.isInverse()) {
            //add to sprint
            return addBacklogToSprint(backlog, sprint);
        } else {
            //remove from sprint
            return removeBacklogFromSprint(backlog, sprint);
        }
    }

    private boolean removeBacklogFromSprint(Backlog backlog, Sprint sprint) {
        if (backlog.sprint().equals(sprint)) {
            backlog.updateSprint(null).saveOrUpdate();
            log.debug(SecurityUtils.currUser().displayName() + " remove the backlog[" + backlog + "] from sprint[" + sprint + "]");
            return true;
        } else {
            //different sprint
            return false;
        }
    }

    private boolean addBacklogToSprint(Backlog backlog, Sprint sprint) {
        if (backlog.sprint() == null) {
            backlog.updateSprint(sprint).saveOrUpdate();
            log.debug(SecurityUtils.currUser().displayName() + " add the backlog[" + backlog + "] to sprint[" + sprint + "]");
            return true;
        } else {
            //the backlog already related sprint
            return false;
        }
    }
}