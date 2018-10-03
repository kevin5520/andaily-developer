package com.andaily.domain.developer;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.shared.Repository;
import com.andaily.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-8-5
 *
 * @author Shengzhao Li
 */
public interface BacklogRepository extends Repository {

    Backlog findByGuid(String guid);

    void saveBacklog(Backlog backlog);

    void updateBacklog(Backlog backlog);

    List<Backlog> findAvailableBacklogs(String projectGuid);

    void updateSprintBacklogs(@Param("sprintGuid") String sprintGuid, @Param("backlogGuids") List<String> backlogGuids, @Param("user") User user);

    void cleanBacklogsSprint(@Param("backlogs") List<Backlog> backlogs);

    List<Backlog> findSprintBacklogs(String sprintGuid);

    List<Backlog> findOverviewBacklogs(Map<String, Object> map);

    int totalOverviewBacklogs(Map<String, Object> map);

    List<Backlog> findUnusedBacklogsByProject(Project project);

    int maxBacklogNumber();
}
