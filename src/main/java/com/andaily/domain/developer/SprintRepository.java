package com.andaily.domain.developer;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.shared.Repository;
import com.andaily.domain.user.TestTable;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.mybatis.data.SprintTaskTimeData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-8-5
 *
 * @author Shengzhao Li
 */
public interface SprintRepository extends Repository {

    Sprint findByGuid(String guid);

    SprintTask findTaskByGuid(String guid);

    void saveSprint(Sprint sprint);

    void updateSprint(Sprint sprint);

    void saveSprintTask(SprintTask task);

    void updateSprintTask(SprintTask task);

    List<Sprint> findSprints(boolean archived);

    List<Sprint> findAvailableSprints(Map<String, Object> map);

    List<SprintTask> findSprintTasks(Map<String, Object> map);

    int totalSprintTasks(Map<String, Object> map);

    Sprint findByName(String name);

    String findSprintName(String sprintGuid);

    int totalTasksBySprintAndStatus(@Param("sprintGuid") String sprintGuid, @Param("status") SprintTaskStatus status);

    void updateTaskStatus(@Param("guid") String guid, @Param("status") SprintTaskStatus status);

    List<Sprint> findOverviewSprints(Map<String, Object> map);

    int totalOverviewSprints(Map<String, Object> map);

    void saveSprintMeeting(SprintMeeting meeting);

    void saveSprintMeetingDeveloper(SprintMeetingDeveloper sprintMeetingDeveloper);

    SprintMeeting findMeetingByGuid(String guid);

    Date findSprintLastTaskTime(Sprint sprint);

    int findSpecifyDayFinishedTaskTimes(@Param("sprint") Sprint sprint, @Param("date") Date date);

    void updateSprintMeeting(SprintMeeting meeting);

    List<SprintMeeting> findLatestMeetings(@Param("sprintGuid") String sprintGuid, @Param("size") int size);

    List<SprintMeeting> findMeetings(Map<String, Object> map);

    int totalMeetings(Map<String, Object> map);

    void saveSprintTaskMoveRecord(SprintTaskMoveRecord record);

    List<Sprint> findExcludeGuidAndStatusSprints(@Param("excludeGuid") String excludeGuid, @Param("projectGuid") String projectGuid, @Param("statuses") SprintStatus... statuses);

    int countSprintTaskMoveRecords(SprintTask task);

    void updateProjectSprintsNoDefault(Project project);

    void updateSprintTaskExecutor(@Param("guid") String guid, @Param("executor") User executor);

    int totalSprintMeetings(@Param("sprintGuid") String sprintGuid, @Param("type") SprintMeetingType type);

    List<Sprint> findProjectSimpleSprints(@Param("projectGuid") String projectGuid, @Param("statuses") SprintStatus... statuses);

    //dateAsText:  yyyy-MM-dd
    int sumDeveloperEstimateTime(@Param("developerGuid") String developerGuid, @Param("dateAsText") String dateAsText);

    //dateAsText:  yyyy-MM-dd
    SprintTaskTimeData sumDeveloperTaskTimes(@Param("developerGuid") String developerGuid, @Param("dateAsText") String dateAsText, @Param("sprint") Sprint sprint);

    void saveSprintTaskComment(SprintTaskComment comment);

    List<SprintTaskComment> findCommentsByTaskGuid(String taskGuid);

    SprintTaskComment findSprintTaskCommentByGuid(String guid);

    void deleteSprintTaskComment(SprintTaskComment comment);

    int totalSprintTasksOfBacklog(Backlog backlog);

    Date lastFinishedTaskTimeBySprint(Sprint sprint);
    
    
}
