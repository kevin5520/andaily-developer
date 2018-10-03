package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.commons.CommonsRepository;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.GuidGenerator;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Date: 13-8-5
 *
 * @author Shengzhao Li
 */
public class BacklogRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CommonsRepository commonsRepository;

    @Test
    public void findByGuid() {
        String guid = "dooaod";
        Backlog result = backlogRepository.findByGuid(guid);

        assertNull(result);
    }

    @Test
    public void maxBacklogNumber() {

        final int number = backlogRepository.maxBacklogNumber();
        assertEquals(number, 0);

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS).number(1);
        backlogRepository.saveBacklog(backlog);

        final int i = backlogRepository.maxBacklogNumber();
        assertEquals(i, 1);

    }

    @Test
    public void findUnusedBacklogsByProject() {
        Project project = new Project("p", "P", "p os p");
        project.saveOrUpdate();

        final List<Backlog> list = backlogRepository.findUnusedBacklogsByProject(project);
        assertEquals(list.size(), 0);
    }

    @Test
    public void findOverviewBacklogs() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("priority", SprintPriority.DEFAULT);
        map.put("type", BacklogType.NEEDS);
        map.put("sprintGuid", GuidGenerator.generate());
        map.put("number", "3");

        List<Backlog> list = backlogRepository.findOverviewBacklogs(map);
        assertNotNull(list);
    }

    @Test
    public void totalOverviewBacklogs() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("priority", SprintPriority.DEFAULT);
        map.put("type", BacklogType.NEEDS);
        map.put("sprintGuid", GuidGenerator.generate());
        map.put("number", "3");

        int count = backlogRepository.totalOverviewBacklogs(map);
        assertEquals(count, 0);
    }

    @Test
    public void findSprintBacklogs() {
        String guid = "dooaod";
        List<Backlog> list = backlogRepository.findSprintBacklogs(guid);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void updateSprintBacklogs() {
        List<String> backlogGuids = new ArrayList<String>();

        Sprint sprint = new Sprint("test sprint", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);

        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator);
        backlogRepository.saveBacklog(backlog);

        backlogGuids.add(backlog.guid());
        String guid = sprint.guid();

        backlogRepository.updateSprintBacklogs(guid, backlogGuids, SecurityUtils.currUser());

        Backlog result = backlogRepository.findByGuid(backlog.guid());
        assertNotNull(result);
        assertNotNull(result.sprint());

    }

    @Test
    public void cleanBacklogsSprint() {
        List<String> backlogGuids = new ArrayList<String>();

        Sprint sprint = new Sprint("test sprint", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);

        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator);
        backlogRepository.saveBacklog(backlog);

        backlogGuids.add(backlog.guid());
        String guid = sprint.guid();

        backlogRepository.updateSprintBacklogs(guid, backlogGuids, SecurityUtils.currUser());

        Backlog result = backlogRepository.findByGuid(backlog.guid());
        assertNotNull(result);
        assertNotNull(result.sprint());

        backlogRepository.cleanBacklogsSprint(Arrays.asList(result));

        Backlog result2 = backlogRepository.findByGuid(backlog.guid());
        assertNotNull(result2);
        assertNull(result2.sprint());

    }

    @Test
    public void findAvailableBacklogs() {
        List<Backlog> list = backlogRepository.findAvailableBacklogs(GuidGenerator.generate());
        assertNotNull(list);
        assertTrue(list.isEmpty());

        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Project project = new Project("test", "test", "test");
        project.saveOrUpdate();
        project = projectRepository.findByGuid(project.guid());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator).updateProject(project);
        backlogRepository.saveBacklog(backlog);

        list = backlogRepository.findAvailableBacklogs(project.guid());
        assertNotNull(list);
        assertTrue(list.size() == 1);
    }

    @Test
    public void saveBacklog() {
        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator);
        backlogRepository.saveBacklog(backlog);

        Backlog result = backlogRepository.findByGuid(backlog.guid());
        assertNotNull(result);
        assertNotNull(result.createTime());

        assertNotNull(result.creator());
        assertEquals(result.priority(), SprintPriority.DEFAULT);

        BacklogDocument document = new BacklogDocument(result, null);
        commonsRepository.saveBacklogDocument(document);

        result = backlogRepository.findByGuid(backlog.guid());
        assertEquals(result.documents().size(), 1);
    }

    @Test
    public void updateBacklog() {
        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator);
        backlogRepository.saveBacklog(backlog);

        Backlog result = backlogRepository.findByGuid(backlog.guid());
        assertNotNull(result);
        assertNotNull(result.createTime());

        assertNotNull(result.creator());
        assertEquals(result.priority(), SprintPriority.DEFAULT);

        result.updateContent("123").updateJoinSprintTime(DateUtils.now());

        backlogRepository.updateBacklog(result);

        Backlog result2 = backlogRepository.findByGuid(result.guid());
        assertNotNull(result2);
        assertNotNull(result2.joinSprintTime());
        assertEquals(result2.content(), "123");

    }
}
