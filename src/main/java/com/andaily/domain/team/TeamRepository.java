package com.andaily.domain.team;

import com.andaily.domain.shared.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-11-20
 *
 * @author Shengzhao Li
 */
public interface TeamRepository extends Repository {

    Team findByGuid(String guid);

    void saveTeam(Team team);

    void updateTeam(Team team);

    void saveTeamProject(TeamProject teamProject);

    void deleteTeamProject(TeamProject teamProject);

    List<Team> findAvailableTeams();

    List<Team> findOverviewTeams(Map<String, Object> map);

    int totalOverviewTeams(Map<String, Object> map);

    void deleteTeamProjects(@Param("teamProjects") List<TeamProject> teamProjects);

    List<Team> findTeamsByProjectGuid(String projectGuid);
}
