package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.shared.GuidGenerator;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamProject;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Date: 13-11-21
 *
 * @author Shengzhao Li
 */
public class TeamRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Test
    public void findTeamsByProjectGuid() {
        final List<Team> list = teamRepository.findTeamsByProjectGuid(GuidGenerator.generate());
        assertTrue(list.isEmpty());
    }

    @Test
    public void findByGuid() {
        String guid = GuidGenerator.generate();
        Team team = teamRepository.findByGuid(guid);

        assertNull(team);

        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        team = new Team("team", developerRepository.findByGuid(user.guid()));
        teamRepository.saveTeam(team);

        final Team result = teamRepository.findByGuid(team.guid());
        assertNotNull(result);
        assertNotNull(result.name());
        assertNotNull(result.creator());

    }

    @Test
    public void findAvailableTeams() {

        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        Team team = new Team("team", developerRepository.findByGuid(user.guid()));
        teamRepository.saveTeam(team);

        final Team result = teamRepository.findByGuid(team.guid());
        assertNotNull(result);

        final List<Team> teams = teamRepository.findAvailableTeams();
        assertEquals(teams.size(), 1);

    }

    @Test
    public void deleteTeamProjects() {
        teamRepository.deleteTeamProjects(Arrays.asList(new TeamProject()));
    }

    @Test
    public void findOverviewTeams() {

        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        Team team = new Team("team", developerRepository.findByGuid(user.guid()));
        teamRepository.saveTeam(team);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("archived", false);
        map.put("name", "%tdd%");

        final List<Team> list = teamRepository.findOverviewTeams(map);
        assertTrue(list.size() == 0);
    }

    @Test
    public void totalOverviewTeams() {

        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        Team team = new Team("team", developerRepository.findByGuid(user.guid()));
        teamRepository.saveTeam(team);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("archived", false);
        map.put("name", "%t%");

        int size = teamRepository.totalOverviewTeams(map);
        assertTrue(size == 1);
    }


    @Test
    public void updateTeam() {
        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        Team team = new Team("team", developerRepository.findByGuid(user.guid()));
        teamRepository.saveTeam(team);

        Team result = teamRepository.findByGuid(team.guid());
        final String newName = "new name";
        result.name(newName);

        teamRepository.updateTeam(result);


        result = teamRepository.findByGuid(result.guid());
        assertEquals(result.name(), newName);

    }

    @Test
    public void saveTeamProject() {
        Developer user = new Developer("sas@dd.com", "pass", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(user);

        final Developer developer = developerRepository.findByGuid(user.guid());
        Team team = new Team("team", developer);
        teamRepository.saveTeam(team);

        Team result = teamRepository.findByGuid(team.guid());

        Project project = new Project("project", "PD", "desc");
        projectRepository.saveProject(project);

        TeamProject teamProject = new TeamProject(result, projectRepository.findByGuid(project.guid()));
        teamRepository.saveTeamProject(teamProject);

        developer.team(result);
        developerRepository.updateDeveloper(developer);

        final Developer developer1 = developerRepository.findByGuid(developer.guid());
        assertNotNull(developer1.team());

        team = teamRepository.findByGuid(team.guid());
        final List<TeamProject> teamProjects = team.teamProjects();
        assertTrue(teamProjects.size() == 1);

        final List<Developer> members = team.members();
        assertTrue(members.size() == 1);
    }

}
