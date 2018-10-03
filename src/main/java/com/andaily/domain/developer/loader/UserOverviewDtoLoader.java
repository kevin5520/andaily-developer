package com.andaily.domain.developer.loader;

import com.andaily.domain.dto.SimpleDto;
import com.andaily.domain.dto.user.UserDto;
import com.andaily.domain.dto.user.UserOverviewDto;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.infrastructure.mybatis.data.ScrumTermData;
import com.andaily.web.context.BeanProvider;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-11-26
 *
 * @author Shengzhao Li
 */
public class UserOverviewDtoLoader {

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    private UserOverviewDto userOverviewDto;

    public UserOverviewDtoLoader(UserOverviewDto userOverviewDto) {
        this.userOverviewDto = userOverviewDto;
    }

    public UserOverviewDto load() {
        loadScrumTermDataList();
        loadTeams();

        final Map<String, Object> map = userOverviewDto.defaultQueryMap();
        return userOverviewDto.load(new PaginatedLoader<UserDto>() {
            @Override
            public List<UserDto> loadList() {
                List<User> users = userRepository.findOverviewUsers(map);
                return UserDto.toDtos(users);
            }

            @Override
            public int loadTotalSize() {
                return userRepository.totalOverviewUsers(map);
            }
        });
    }

    private void loadTeams() {
        final List<Team> availableTeams = teamRepository.findAvailableTeams();
        final List<SimpleDto> teams = userOverviewDto.getTeams();
        for (Team availableTeam : availableTeams) {
            teams.add(new SimpleDto(availableTeam.guid(), availableTeam.name()));
        }
    }

    private void loadScrumTermDataList() {
        List<ScrumTermData> datas = userRepository.findScrumTermDatas();
        final ScrumTerm[] scrumTerms = ScrumTerm.values();
        if (datas.size() < scrumTerms.length) {
            for (ScrumTerm term : scrumTerms) {
                ScrumTermData newScrumTermData = new ScrumTermData(term);
                if (!datas.contains(newScrumTermData)) {
                    datas.add(newScrumTermData);
                }
            }
        }
        userOverviewDto.setTermDataList(datas);
    }
}
