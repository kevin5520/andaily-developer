package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.team.TeamFormDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * Date: 14-1-6
 *
 * @author Shengzhao Li
 */
public class TeamFormDtoLoader {

    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private String guid;

    public TeamFormDtoLoader(String guid) {
        this.guid = guid;
    }

    public TeamFormDto load() {
        TeamFormDto formDto = new TeamFormDto(guid);
        if (!formDto.isNewly()) {
            final Team team = teamRepository.findByGuid(guid);
            formDto = new TeamFormDto(team);
        }
        loadDevelopers(formDto);
        loadProjects(formDto);
        return formDto;
    }

    private void loadProjects(TeamFormDto formDto) {
        formDto.setProjects(projectRepository.findAllSimpleProjects());
        //reset added projects
        final List<ProjectDto> projectDtos = formDto.getProjectDtos();
        for (ProjectDto projectDto : projectDtos) {
            formDto.getProjectGuids().add(projectDto.getGuid());
        }
    }

    private void loadDevelopers(TeamFormDto formDto) {
        List<Developer> developers = developerRepository.findEmptyDevelopers(ScrumTerm.MASTER, ScrumTerm.MEMBER);
        final List<DeveloperDto> developerDtoList = DeveloperDto.toDeveloperDtos(developers);
        //add exist if have
        final List<DeveloperDto> teamMembers = formDto.getTeamMembers();
        developerDtoList.addAll(teamMembers);
        if (!teamMembers.isEmpty()) {
            //reset added members
            for (DeveloperDto teamMember : teamMembers) {
                if (teamMember.getScrumTerm().isMaster()) {
                    formDto.getMasters().add(teamMember.getGuid());
                } else if (teamMember.getScrumTerm().isMember()) {
                    formDto.getMembers().add(teamMember.getGuid());
                }
            }
        }
        formDto.setDeveloperDtoList(developerDtoList);
    }
}
