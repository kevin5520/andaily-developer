package com.andaily.domain.developer.project;

import com.andaily.domain.shared.Repository;
import com.andaily.domain.team.Team;
import com.andaily.domain.user.Developer;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-10-16
 *
 * @author Shengzhao Li
 */
public interface ProjectRepository extends Repository {

    Project findByGuid(String guid);

    void saveProject(Project project);

    void updateProject(Project project);

    List<Project> findOverviewProjects(Map<String, Object> map);

    int totalOverviewProjects(Map<String, Object> map);

    void archiveProject(@Param("guid") String guid, @Param("archived") boolean archived);

    List<SimpleProjectData> findAllSimpleProjects();

    List<SimpleProjectData> findAvailableSimpleProjects(Map<String, Object> map);

    SimpleProjectData findSimpleProject(String projectGuid);

    List<Project> findByGuids(@Param("guids") List<String> guids);

    void saveProjectProductOwner(ProjectProductOwner projectProductOwner);

    void deleteProjectProductOwners(@Param("productOwners") List<ProjectProductOwner> productOwners);

    List<SimpleProjectData> findSimpleProjectsByTeam(Team team);

    int totalTasksOfProject(Project project);

    List<SimpleProjectData> findSimpleProjectsByDeveloper(Developer developer);

    int maxTaskNumberOfProject(Project project);
}
