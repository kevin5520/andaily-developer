package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.SprintFormBacklogDto;
import com.andaily.domain.dto.developer.SprintFormDto;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-8-16
 *
 * @author Shengzhao Li
 */
public class SprintFormPersister {


    private static LogHelper log = LogHelper.create(SprintFormPersister.class);

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    private SprintFormDto sprintFormDto;
    private List<String> backlogGuids;

    public SprintFormPersister(SprintFormDto sprintFormDto) {
        this.sprintFormDto = sprintFormDto;
        initialBacklogGuids();
    }

    private void initialBacklogGuids() {
        this.backlogGuids = new ArrayList<>();
        List<SprintFormBacklogDto> backlogs = sprintFormDto.getBacklogs();
        for (SprintFormBacklogDto backlog : backlogs) {
            String guid = backlog.getGuid();
            if (StringUtils.isNotEmpty(guid)) {
                this.backlogGuids.add(guid);
            }
        }
    }

    public String persist() {
        if (sprintFormDto.isNewly()) {
            return createSprint();
        } else {
            return updateSprint();
        }
    }

    private String updateSprint() {
        Sprint sprint = sprintRepository.findByGuid(sprintFormDto.getGuid());
        updateValues(sprint);
        sprintRepository.updateSprint(sprint);
        //handle backlogs
        //1. clean old reference
        List<Backlog> backlogs = sprint.backlogs();
        if (!backlogs.isEmpty()) {
            backlogRepository.cleanBacklogsSprint(backlogs);
        }
        //2. update new backlogs
        updateBacklogs(sprint.guid());
        log.debug(SecurityUtils.currUser().displayName() + " update the Sprint [" + sprint.name() + "]");
        return sprint.guid();
    }

    private String createSprint() {
        Sprint newSprint = new Sprint().updateCreator(SecurityUtils.currUser());
        updateValues(newSprint);
        sprintRepository.saveSprint(newSprint);
        //update backlogs
        updateBacklogs(newSprint.guid());
        log.debug(SecurityUtils.currUser().displayName() + " create the Sprint [" + newSprint.name() + "]");
        LogHandler.createSprintActivityLog("create the sprint [" + newSprint.name() + "]", sprintRepository.findByGuid(newSprint.guid()));
        return newSprint.guid();
    }

    private void updateBacklogs(String sprintGuid) {
        if (!this.backlogGuids.isEmpty()) {
            backlogRepository.updateSprintBacklogs(sprintGuid, this.backlogGuids, SecurityUtils.currUser());
        }
    }

    private void updateValues(Sprint sprint) {
        sprint.updateName(sprintFormDto.getName()).updateDescription(sprintFormDto.getDescription());

        Date startDate = DateUtils.getDate(sprintFormDto.getStartDate());
        Date deadline = DateUtils.getDate(sprintFormDto.getDeadline());
        sprint.updateStartDate(startDate).updateDeadline(deadline);

        Project project = projectRepository.findByGuid(sprintFormDto.getProjectGuid());
        sprint.updateProject(project);

        final Team team = teamRepository.findByGuid(sprintFormDto.getTeamGuid());
        sprint.executeTeam(team);
    }
}
