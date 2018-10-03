package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectProductOwner;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.AbstractRepositoryTest;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Date: 13-10-16
 *
 * @author Shengzhao Li
 */
public class ProjectRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Test
    public void findSimpleProjectsByTeam() {
        Team team = new Team("t", null);
        team.saveOrUpdate();
        team = teamRepository.findByGuid(team.guid());

        final List<SimpleProjectData> list = projectRepository.findSimpleProjectsByTeam(team);
        assertTrue(list.isEmpty());
    }

    @Test
    public void findSimpleProjectsByDeveloper() {
        Developer developer = new Developer("dev@andaily.com", "123456", "test", "123233334", ScrumTerm.PRODUCT_OWNER);
        developer.saveOrUpdate();

        final List<SimpleProjectData> list = projectRepository.findSimpleProjectsByDeveloper(developerRepository.findByGuid(developer.guid()));
        assertTrue(list.isEmpty());
    }

    @Test
    public void totalTasksOfProject() {
        Project p = new Project();
        p.saveOrUpdate();
        p = projectRepository.findByGuid(p.guid());

        final int count = projectRepository.totalTasksOfProject(p);
        assertEquals(count, 0);
    }

    @Test
    public void maxTaskNumberOfProject() {
        Project p = new Project();
        p.saveOrUpdate();
        p = projectRepository.findByGuid(p.guid());

        final int number = projectRepository.maxTaskNumberOfProject(p);
        assertEquals(number, 0);
    }

    @Test
    public void findAvailableSimpleProjects() {
        Map<String, Object> map = new HashMap<>();
        map.put("currUser", SecurityUtils.currUser());
        map.put("isProductOwner", "yes");
        map.put("isTeamMember", "yes");

        final List<SimpleProjectData> list = projectRepository.findAvailableSimpleProjects(map);
        assertTrue(list.isEmpty());
    }

    @Test
    public void deleteProjectProductOwners() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        Developer developer = new Developer("dev@andaily.com", "123456", "test", "123233334", ScrumTerm.PRODUCT_OWNER);
        developer.saveOrUpdate();

        ProjectProductOwner owner = new ProjectProductOwner(projectRepository.findByGuid(project.guid()), developerRepository.findByGuid(developer.guid()));
        owner.saveOrUpdate();

        projectRepository.deleteProjectProductOwners(Arrays.asList(owner));

    }

    @Test
    public void findByGuid() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        Developer developer = new Developer("dev@andaily.com", "123456", "test", "123233334", ScrumTerm.PRODUCT_OWNER);
        developer.saveOrUpdate();

        ProjectProductOwner owner = new ProjectProductOwner(projectRepository.findByGuid(project.guid()), developerRepository.findByGuid(developer.guid()));
        owner.saveOrUpdate();

        Project result = projectRepository.findByGuid(project.guid());
        assertNotNull(result);
        assertNotNull(result.name());

        assertEquals(result.productOwners().size(), 1);
    }

    @Test
    public void findByGuids() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        final List<Project> list = projectRepository.findByGuids(Arrays.asList(project.guid()));
        assertNotNull(list);
        assertEquals(list.size(), 1);
    }

    @Test
    public void saveProject() {
        Project project = new Project("Andaily developer", "AD", "desc").finishDate(DateUtils.now());
        projectRepository.saveProject(project);

        Project result = projectRepository.findByGuid(project.guid());
        assertNotNull(result);
        assertNotNull(result.name());
        assertNotNull(result.finishDate());

        Sprint sprint = new Sprint("sprint", DateUtils.now(), DateUtils.now()).updateProject(result);
        sprintRepository.saveSprint(sprint);

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "con", BacklogType.NEEDS).updateProject(result);
        backlogRepository.saveBacklog(backlog);

        result = projectRepository.findByGuid(project.guid());
        assertNotNull(result);
        assertNotNull(result.name());

        assertEquals(result.sprints().size(), 1);
        assertEquals(result.backlogs().size(), 1);
    }


    @Test
    public void findOverviewProjects() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("teamGuid", null);
        map.put("productOwnerGuid", null);
        map.put("name", "%deve%");

        List<Project> list = projectRepository.findOverviewProjects(map);
        assertNotNull(list);
        assertEquals(list.size(), 1);

    }

    @Test
    public void totalOverviewProjects() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("teamGuid", null);
        map.put("productOwnerGuid", null);
        map.put("name", "%de0ve%");

        int count = projectRepository.totalOverviewProjects(map);
        assertEquals(count, 0);

    }

    @Test
    public void archiveProject() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        projectRepository.archiveProject(project.guid(), true);

        Project result = projectRepository.findByGuid(project.guid());
        assertNotNull(result);
        assertTrue(result.archived());

    }

    @Test
    public void findAllSimpleProjects() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        List<SimpleProjectData> list = projectRepository.findAllSimpleProjects();
        assertNotNull(list);
        assertEquals(list.size(), 1);

    }

    @Test
    public void findSimpleProject() {
        Project project = new Project("Andaily developer", "AD", "desc");
        projectRepository.saveProject(project);

        SimpleProjectData result = projectRepository.findSimpleProject(project.guid());
        assertNotNull(result);

    }

}
