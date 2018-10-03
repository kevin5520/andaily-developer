package com.andaily.service.impl;

import com.andaily.domain.developer.loader.MyTeamDtoLoader;
import com.andaily.domain.developer.loader.TeamFormDtoLoader;
import com.andaily.domain.developer.operation.TeamFormDtoPersister;
import com.andaily.domain.dto.team.MyTeamDto;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.dto.team.TeamFormDto;
import com.andaily.domain.dto.team.TeamOverviewDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-12-17
 *
 * @author Shengzhao Li
 */
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;


    @Override
    public TeamOverviewDto loadTeamOverviewDto(TeamOverviewDto teamOverviewDto) {
        final Map<String, Object> map = teamOverviewDto.defaultQueryMap();
        return teamOverviewDto.load(new PaginatedLoader<TeamDto>() {
            @Override
            public List<TeamDto> loadList() {
                List<Team> teams = teamRepository.findOverviewTeams(map);
                return TeamDto.toDtos(teams);
            }

            @Override
            public int loadTotalSize() {
                return teamRepository.totalOverviewTeams(map);
            }
        });
    }

    @Override
    public TeamFormDto loadTeamFormDto(String guid) {
        TeamFormDtoLoader loader = new TeamFormDtoLoader(guid);
        return loader.load();
    }

    @Override
    public void persistTeamFormDto(TeamFormDto teamFormDto) {
        TeamFormDtoPersister persister = new TeamFormDtoPersister(teamFormDto);
        persister.persist();
    }

    @Override
    public void archiveTeam(String guid) {
        Team team = teamRepository.findByGuid(guid);
        team.archiveMe();
    }

    @Override
    public MyTeamDto loadMyTeamDto() {
        MyTeamDtoLoader myTeamDtoLoader = new MyTeamDtoLoader();
        return myTeamDtoLoader.load();
    }
}
