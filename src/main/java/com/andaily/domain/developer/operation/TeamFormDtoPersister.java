package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.team.TeamFormDto;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamProject;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class TeamFormDtoPersister {

    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private TeamFormDto teamFormDto;

    public TeamFormDtoPersister(TeamFormDto teamFormDto) {
        this.teamFormDto = teamFormDto;
    }

    public void persist() {
        if (teamFormDto.isNewly()) {
            saveTeam();
        } else {
            updateTeam();
        }
    }

    private void updateTeam() {
        Team team = teamRepository.findByGuid(teamFormDto.getGuid());
        team.name(teamFormDto.getName())
                .description(teamFormDto.getDescription());

        persistProjects(team);
        persistDevelopers(team);
        team.saveOrUpdate();
    }

    private void saveTeam() {
        Team team = teamFormDto.toDomain();
        team.saveOrUpdate();
        team = teamRepository.findByGuid(team.guid());

        persistProjects(team);
        persistDevelopers(team);
    }

    private void persistDevelopers(Team team) {
        List<Developer> members = team.members();
        if (!members.isEmpty()) {
            developerRepository.cleanDevelopersTeam(members);
            members.clear();
        }

        List<String> guids = teamFormDto.getMasters();
        guids.addAll(teamFormDto.getMembers());
        developerRepository.updateDevelopersTeam(guids, team);
    }

    private void persistProjects(Team team) {
        List<TeamProject> teamProjects = team.teamProjects();
        if (!teamProjects.isEmpty()) {
            teamRepository.deleteTeamProjects(teamProjects);
            teamProjects.clear();
        }

        final List<String> projectGuids = teamFormDto.getProjectGuids();
        if (projectGuids != null && !projectGuids.isEmpty()) {
            List<Project> projects = projectRepository.findByGuids(projectGuids);
            for (Project project : projects) {
                final TeamProject teamProject = new TeamProject(team, project);
                teamRepository.saveTeamProject(teamProject);
            }
        }

    }
}