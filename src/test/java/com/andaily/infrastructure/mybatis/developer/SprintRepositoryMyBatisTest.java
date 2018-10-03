package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.*;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.GuidGenerator;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.*;
import com.andaily.infrastructure.AbstractRepositoryTest;
import com.andaily.infrastructure.mybatis.data.SprintTaskTimeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Date: 13-8-5
 *
 * @author Shengzhao Li
 */
public class SprintRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findByGuid() {
        String guid = "dooaod";
        Sprint result = sprintRepository.findByGuid(guid);

        assertNull(result);
    }

    @Test
    public void lastFinishedTaskTimeBySprint() {
        Sprint sprint = new Sprint("test", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);
        sprint = sprintRepository.findByGuid(sprint.guid());

        SprintTask task = new SprintTask().updateSprint(sprint);
        task.saveOrUpdate();

        final Date date = sprintRepository.lastFinishedTaskTimeBySprint(sprint);
        assertNull(date);


        SprintTask task2 = new SprintTask().updateSprint(sprint).status(SprintTaskStatus.FINISHED);
        task2.saveOrUpdate();


        final Date date1 = sprintRepository.lastFinishedTaskTimeBySprint(sprint);
        assertNull(date1);

    }

    @Test
    public void saveSprintTaskComment() {
        SprintTask task = new SprintTask();
        task.saveOrUpdate();
        task = sprintRepository.findTaskByGuid(task.guid());

        SprintTaskComment comment = new SprintTaskComment()
                .task(task).content("testing");
        comment.saveOrUpdate();

        new SprintTaskComment().task(task).content("test....").saveOrUpdate();

        final List<SprintTaskComment> list = sprintRepository.findCommentsByTaskGuid(task.guid());
        assertEquals(list.size(), 2);

        final SprintTaskComment comment1 = sprintRepository.findSprintTaskCommentByGuid(comment.guid());
        assertNotNull(comment1);
    }

    @Test
    public void totalSprintTasksOfBacklog() {
        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "odesd", BacklogType.NEEDS);
        backlog.saveOrUpdate();
        backlog = backlogRepository.findByGuid(backlog.guid());

        SprintTask task = new SprintTask().updateBacklog(backlog);
        task.saveOrUpdate();
        SprintTask task2 = new SprintTask();
        task2.saveOrUpdate();


        final int count = sprintRepository.totalSprintTasksOfBacklog(backlog);
        assertEquals(count, 1);

    }

    @Test
    public void findProjectSimpleSprints() {
        final List<Sprint> list = sprintRepository.findProjectSimpleSprints(GuidGenerator.generate(), SprintStatus.PENDING);
        assertTrue(list.isEmpty());
    }

    @Test
    public void totalSprintMeetings() {

        final int i = sprintRepository.totalSprintMeetings(GuidGenerator.generate(), SprintMeetingType.DAILY_STANDING);
        assertEquals(i, 0);
    }

    @Test
    public void sumDeveloperEstimateTime() {

        final int times = sprintRepository.sumDeveloperEstimateTime(GuidGenerator.generate(), "2014-06-11");
        assertEquals(times, 0);
    }

    @Test
    public void sumDeveloperTaskTimes() {
        Sprint sprint = new Sprint("test", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);
        sprint = sprintRepository.findByGuid(sprint.guid());

        SprintTaskTimeData data = sprintRepository.sumDeveloperTaskTimes(GuidGenerator.generate(), "2014-06-11", sprint);
        assertNotNull(data);
        assertEquals(data.getActualUsedTime(), 0);
    }

    @Test
    public void updateSprintTaskExecutor() {
        Developer developer = new Developer("sodo@dd.com", "233", "nick", "22122", ScrumTerm.MEMBER);
        developer.saveOrUpdate();
        developer = developerRepository.findByGuid(developer.guid());

        SprintTask task = new SprintTask();
        sprintRepository.saveSprintTask(task);
        sprintRepository.updateSprintTaskExecutor(task.guid(), developer);

        final SprintTask task1 = sprintRepository.findTaskByGuid(task.guid());
        assertNotNull(task1.executor());
    }

    @Test
    public void findAvailableSprints() {
        Map<String, Object> map = new HashMap<>();
        map.put("currUser", SecurityUtils.currUser());
        map.put("isProductOwner", "yes");
        map.put("isTeamMember", "yes");

        final List<Sprint> list = sprintRepository.findAvailableSprints(map);
        assertEquals(list.size(), 0);
    }

    @Test
    public void updateProjectSprintsNoDefault() {
        Project project = new Project("no", "NO", "desc");
        project.saveOrUpdate();

        sprintRepository.updateProjectSprintsNoDefault(projectRepository.findByGuid(project.guid()));
    }

    @Test
    public void saveSprintTaskMoveRecord() {
        SprintTaskMoveRecord record = new SprintTaskMoveRecord();
        sprintRepository.saveSprintTaskMoveRecord(record);
    }

    @Test
    public void countSprintTaskMoveRecords() {
        SprintTask task = new SprintTask();
        sprintRepository.saveSprintTask(task);

        int count = sprintRepository.countSprintTaskMoveRecords(task);
        assertEquals(count, 0);
    }

    @Test
    public void findExcludeGuidAndStatusSprints() {
        Project project = new Project("afsp", "od", "desc");
        project.saveOrUpdate();
        String projectGuid = project.guid();

        List<Sprint> list = sprintRepository.findExcludeGuidAndStatusSprints("osesd", projectGuid, SprintStatus.CREATED, SprintStatus.PENDING);
        assertTrue(list.isEmpty());

        Sprint sprint = new Sprint();
        sprint.updateProject(projectRepository.findByGuid(projectGuid));
        sprintRepository.saveSprint(sprint);

        list = sprintRepository.findExcludeGuidAndStatusSprints("osesd", projectGuid, SprintStatus.CREATED, SprintStatus.PENDING);
        assertFalse(list.isEmpty());

    }

    @Test
    public void findMeetings() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("type", SprintMeetingType.DAILY_STANDING);
        map.put("sprintGuid", "sprint_guid");

        List<SprintMeeting> list = sprintRepository.findMeetings(map);
        assertTrue(list.isEmpty());
    }

    @Test
    public void totalMeetings() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("type", SprintMeetingType.DAILY_STANDING);
        map.put("sprintGuid", "sprint_guid");

        int count = sprintRepository.totalMeetings(map);
        assertTrue(count == 0);
    }

    @Test
    public void findLatestMeetings() {
        Sprint sprint = new Sprint();
        sprintRepository.saveSprint(sprint);

        List<SprintMeeting> list = sprintRepository.findLatestMeetings(sprint.guid(), 5);
        assertTrue(list.isEmpty());
    }

    @Test
    public void findSprintLastTaskTime() {
        Sprint sprint = new Sprint();
        sprintRepository.saveSprint(sprint);
        sprint = sprintRepository.findByGuid(sprint.guid());

        Date date = sprintRepository.findSprintLastTaskTime(sprint);
        assertNull(date);

    }

    @Test
    public void findSpecifyDayFinishedTaskTimes() {
        Sprint sprint = new Sprint();
        sprintRepository.saveSprint(sprint);
        sprint = sprintRepository.findByGuid(sprint.guid());

        int times = sprintRepository.findSpecifyDayFinishedTaskTimes(sprint, DateUtils.now());
        assertEquals(times, 0);

    }

    @Test
    public void findOverviewSprints() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("status", SprintStatus.CREATED);

        List<Sprint> list = sprintRepository.findOverviewSprints(map);
        assertTrue(list.isEmpty());
    }

    @Test
    public void totalOverviewSprints() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("status", SprintStatus.CREATED);

        int count = sprintRepository.totalOverviewSprints(map);
        assertEquals(0, count);
    }

    @Test
    public void updateTaskStatus() {
        String guid = "dooaod";
        sprintRepository.updateTaskStatus(guid, SprintTaskStatus.PENDING);
    }

    @Test
    public void totalTasksBySprintAndStatus() {
        String guid = "dooaod";
        int count = sprintRepository.totalTasksBySprintAndStatus(guid, SprintTaskStatus.CREATED);
        assertEquals(count, 0);
    }

    @Test
    public void findSprintName() {
        String guid = "dooaod";
        String result = sprintRepository.findSprintName(guid);

        assertNull(result);
    }

    @Test
    public void findByName() {
        String name = "Andaily 1.0";
        Sprint result = sprintRepository.findByName(name);
        assertNull(result);

        Sprint sprint = new Sprint(name, DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);
        result = sprintRepository.findByName(name);
        assertNotNull(result);

    }

    @Test
    public void findSprintTasks() {
        Project project = new Project("test", "test", "test");
        project.saveOrUpdate();
        project = projectRepository.findByGuid(project.guid());

        Sprint sprint = new Sprint("test sprint", DateUtils.now(), DateUtils.now());
        sprint.updateProject(project);
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SprintTaskStatus.CREATED);
        map.put("sprintGuid", sprint.guid());
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 10);
        map.put("startIndex", 0);
        map.put("number", null);
        map.put("findSprintTasks", false);
        map.put("orderBy", "a.finish_time");

        List<SprintTask> tasks = sprintRepository.findSprintTasks(map);
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());

        SprintTask task = new SprintTask("task1", 30, SprintPriority.DEFAULT, false);
        task.updateSprint(sprint1);
        sprintRepository.saveSprintTask(task);

        tasks = sprintRepository.findSprintTasks(map);
        assertNotNull(tasks);
        assertTrue(!tasks.isEmpty());
    }

    @Test
    public void totalSprintTasks() {
        Project project = new Project("test", "test", "test");
        project.saveOrUpdate();
        project = projectRepository.findByGuid(project.guid());

        Sprint sprint = new Sprint("test sprint", DateUtils.now(), DateUtils.now());
        sprint.updateProject(project);
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", SprintTaskStatus.CREATED);
        map.put("sprintGuid", sprint.guid());
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 10);
        map.put("startIndex", 0);
        map.put("number", null);
        map.put("findSprintTasks", false);

        int tasks = sprintRepository.totalSprintTasks(map);
        assertEquals(tasks, 0);

        SprintTask task = new SprintTask("task1", 30, SprintPriority.DEFAULT, false);
        task.updateSprint(sprint1);
        sprintRepository.saveSprintTask(task);

        tasks = sprintRepository.totalSprintTasks(map);
        assertEquals(tasks, 1);
    }

    @Test
    public void findSprints() {
        List<Sprint> list = sprintRepository.findSprints(false);
        assertTrue(list.isEmpty());

        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Sprint sprint = new Sprint("Andaily-1.1", DateUtils.now(), DateUtils.getDate("2013-09-29")).updateCreator(creator);
//        sprint.archived(true);
        sprintRepository.saveSprint(sprint);

        list = sprintRepository.findSprints(false);
        assertTrue(list.size() == 1);
    }

    @Test
    public void findTaskByGuid() {
        SprintTask task = new SprintTask();
        SprintTask result = sprintRepository.findTaskByGuid(task.guid());

        assertNull(result);
    }


    @Test
    public void saveSprint() {
        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Sprint sprint = new Sprint("Andaily-1.1", DateUtils.now(), DateUtils.getDate("2013-09-29")).updateCreator(creator);
        sprintRepository.saveSprint(sprint);

        Sprint result = sprintRepository.findByGuid(sprint.guid());
        assertNotNull(result);
        assertNotNull(result.name());
        assertNotNull(result.creator());

        Backlog backlog = new Backlog(SprintPriority.DEFAULT, "test", BacklogType.BUGS);
        backlog.updateCreator(creator).updateSprint(result);
        backlogRepository.saveBacklog(backlog);

        result.updateStatus(SprintStatus.PENDING).updateName("Andaily-1.1.1");

        sprintRepository.updateSprint(result);

        Sprint sprint1 = sprintRepository.findByGuid(result.guid());
        assertNotNull(sprint1);
        assertEquals(sprint1.status(), SprintStatus.PENDING);
        assertTrue(sprint1.backlogs().size() == 1);
    }

    @Test
    public void saveSprintTask() {
        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Sprint sprint = new Sprint("Andaily-1.1", DateUtils.now(), DateUtils.getDate("2013-09-29")).updateCreator(creator);
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        SprintTask task = new SprintTask("task", 30, SprintPriority.DEFAULT, false).updateSprint(sprint1).updateCreator(creator);
        sprintRepository.saveSprintTask(task);

        SprintTask result = sprintRepository.findTaskByGuid(task.guid());
        assertNotNull(result);
        assertNotNull(result.sprint());
        assertNotNull(result.creator());
    }

    @Test
    public void updateSprintTask() {
        User user = new User("sdodo@emlo.com", "123", "CD");
        userRepository.saveUser(user);
        User creator = userRepository.findByGuid(user.guid());

        Sprint sprint = new Sprint("Andaily-1.1", DateUtils.now(), DateUtils.getDate("2013-09-29")).updateCreator(creator);
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        SprintTask task = new SprintTask("task", 30, SprintPriority.DEFAULT, false).updateSprint(sprint1).updateCreator(creator);
        sprintRepository.saveSprintTask(task);

        SprintTask result = sprintRepository.findTaskByGuid(task.guid());
        assertNotNull(result);
        assertNotNull(result.sprint());
        assertNotNull(result.creator());

        result.updateName("task1");

        sprintRepository.updateSprintTask(result);

        SprintTask result2 = sprintRepository.findTaskByGuid(result.guid());
        assertEquals(result2.name(), "task1");

    }


    @Test
    public void findMeetingByGuid() {
        SprintMeeting result = sprintRepository.findMeetingByGuid("dood");
        assertNull(result);

        Developer developer = new Developer("dds@dd.com", "doo", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(developer);

        Sprint sprint = new Sprint("sprint", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        Developer developer1 = developerRepository.findByGuid(developer.guid());

        SprintMeeting meeting = new SprintMeeting(DateUtils.now(), SprintMeetingType.DAILY_STANDING, "Deploy application");
        meeting.updateSprint(sprint1);
        sprintRepository.saveSprintMeeting(meeting);

        result = sprintRepository.findMeetingByGuid(meeting.guid());
        assertNotNull(result);
        assertNotNull(result.type());
        assertNotNull(result.meetingDate());
        assertNotNull(result.sprint());

        SprintMeetingDeveloper sprintMeetingDeveloper = new SprintMeetingDeveloper(developer1, result);
        sprintRepository.saveSprintMeetingDeveloper(sprintMeetingDeveloper);

        result = sprintRepository.findMeetingByGuid(meeting.guid());
        assertNotNull(result);
        List<SprintMeetingDeveloper> sprintMeetingDevelopers = result.joinDevelopers();
        assertEquals(sprintMeetingDevelopers.size(), 1);
    }

    @Test
    public void updateSprintMeeting() {
        Sprint sprint = new Sprint("sprint", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);
        Sprint sprint1 = sprintRepository.findByGuid(sprint.guid());

        SprintMeeting meeting = new SprintMeeting(DateUtils.now(), SprintMeetingType.DAILY_STANDING, "Deploy application");
        meeting.updateSprint(sprint1);
        sprintRepository.saveSprintMeeting(meeting);

        SprintMeeting meeting2 = sprintRepository.findMeetingByGuid(meeting.guid());
        String newContent = "newContent";
        meeting2.updateType(SprintMeetingType.RETROSPECTIVE).updateContent(newContent).updateMeetingDate(DateUtils.now());

        sprintRepository.updateSprintMeeting(meeting2);

        SprintMeeting meeting1 = sprintRepository.findMeetingByGuid(meeting2.guid());
        assertNotNull(meeting1);
        assertEquals(meeting1.content(), newContent);
    }
}
