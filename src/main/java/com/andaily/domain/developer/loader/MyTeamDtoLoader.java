package com.andaily.domain.developer.loader;

import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.team.MyTeamDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamProject;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * @author Shengzhao Li
 */
public class MyTeamDtoLoader {


    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    public MyTeamDtoLoader() {
    }

    public MyTeamDto load() {
        Team team = SecurityUtils.currUser().team();

        MyTeamDto myTeamDto = new MyTeamDto();
        if (team == null) {
            //Not added team yet.
            myTeamDto.setReferTeam(false);
        } else {
            //Maybe the team data have changed in DB, so flush
            final Team team2 = teamRepository.findByGuid(team.guid());
            loadData(team2, myTeamDto);
        }
        return myTeamDto;
    }

    private void loadData(Team team, MyTeamDto myTeamDto) {
        myTeamDto.setTeamName(team.name())
                .setTeamDescription(team.description());
        //load members
        final List<Developer> members = team.members();
        final List<DeveloperDto> members1 = myTeamDto.getMembers();
        for (Developer member : members) {
            members1.add(new DeveloperDto(member));
        }
        //load projects
        final List<TeamProject> teamProjects = team.teamProjects();
        final List<ProjectDto> projects = myTeamDto.getProjects();
        for (TeamProject teamProject : teamProjects) {
            projects.add(new ProjectDto(teamProject.project()));
        }
    }
}