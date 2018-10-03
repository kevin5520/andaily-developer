package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.SprintFormBacklogDto;
import com.andaily.domain.dto.developer.SprintFormDto;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Date: 13-8-15
 *
 * @author Shengzhao Li
 */
public class SprintFormDtoLoader {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    private String guid;
    private String projectGuid;

    public SprintFormDtoLoader(String guid, String projectGuid) {
        this.guid = guid;
        this.projectGuid = projectGuid;
    }

    public SprintFormDto load() {
        SprintFormDto formDto = new SprintFormDto(guid, projectGuid);
        if (!formDto.isNewly()) {
            formDto = loadFromDb();
        } else {
            initialNewDto(formDto);
        }
        loadProjects(formDto);
        return formDto;
    }

    private void loadProjects(SprintFormDto formDto) {
        final String projectGuid1 = formDto.getProjectGuid();
        final User currUser = SecurityUtils.currUser();

        SimpleProjectData projectData = projectRepository.findSimpleProject(projectGuid1);
        formDto.setAvailableProjects(Arrays.asList(projectData));
        //load the project refer teams
        if (currUser.scrumTerm().isMaster()) {
            formDto.getAvailableTeams().add(new TeamDto(currUser.team(), false));
        } else {
            List<Team> teams = teamRepository.findTeamsByProjectGuid(projectGuid1);
            formDto.setAvailableTeams(TeamDto.toDtos(teams, false));
        }
    }

    private void initialNewDto(SprintFormDto formDto) {
        formDto.initialStartDate();
        loadBacklogs(formDto, Collections.<Backlog>emptyList(), Collections.<Backlog>emptyList());
    }

    private SprintFormDto loadFromDb() {
        Sprint sprint = sprintRepository.findByGuid(guid);
        ScrumSecurityChecker.checkSprint(sprint, SprintStatus.CREATED);
        SprintFormDto formDto = new SprintFormDto(sprint);

        List<Backlog> existBacklogs = sprint.backlogs();
        List<Backlog> referredBacklogs = referredBacklogs(sprint);
        loadBacklogs(formDto, existBacklogs, referredBacklogs);
        return formDto;
    }

    private List<Backlog> referredBacklogs(Sprint sprint) {
        List<Backlog> backlogs = new ArrayList<>();
        List<SprintTask> tasks = sprint.tasks();
        for (SprintTask task : tasks) {
            Backlog backlog = task.backlog();
            if (backlog != null) {
                backlogs.add(backlog);
            }
        }
        return backlogs;
    }

    private void loadBacklogs(SprintFormDto formDto, List<Backlog> existBacklogs, List<Backlog> referredBacklogs) {
        List<SprintFormBacklogDto> backlogDtos = new ArrayList<>();
        int budget = 0;
        for (Backlog existBacklog : existBacklogs) {
            boolean disabled = disabledBacklog(existBacklog, referredBacklogs);
            SprintFormBacklogDto backlogDto = new SprintFormBacklogDto(existBacklog.guid(), existBacklog.content(), true, disabled);
            int estimateTime = existBacklog.estimateTime();
            backlogDto.setEstimateTime(estimateTime);
            backlogDtos.add(backlogDto);
            budget += estimateTime;
        }
        formDto.setBudgetBacklogsTime(budget);

        List<Backlog> availableBacklogs = backlogRepository.findAvailableBacklogs(formDto.getProjectGuid());
        for (Backlog availableBacklog : availableBacklogs) {
            SprintFormBacklogDto backlogDto = new SprintFormBacklogDto(availableBacklog.guid(), availableBacklog.content(), false);
            backlogDto.setEstimateTime(availableBacklog.estimateTime());
            backlogDtos.add(backlogDto);
        }
        formDto.setBacklogs(backlogDtos);
    }

    private boolean disabledBacklog(Backlog existBacklog, List<Backlog> referredBacklogs) {
        return referredBacklogs.contains(existBacklog);
    }
}
