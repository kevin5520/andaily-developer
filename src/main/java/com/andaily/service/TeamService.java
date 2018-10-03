package com.andaily.service;

import com.andaily.domain.dto.team.MyTeamDto;
import com.andaily.domain.dto.team.TeamFormDto;
import com.andaily.domain.dto.team.TeamOverviewDto;

/**
 * Date: 13-12-17
 *
 * @author Shengzhao Li
 */
public interface TeamService {

    TeamOverviewDto loadTeamOverviewDto(TeamOverviewDto teamOverviewDto);

    TeamFormDto loadTeamFormDto(String guid);

    void persistTeamFormDto(TeamFormDto teamFormDto);

    void archiveTeam(String guid);

    MyTeamDto loadMyTeamDto();
}
