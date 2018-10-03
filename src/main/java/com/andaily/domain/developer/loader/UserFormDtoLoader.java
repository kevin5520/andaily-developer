package com.andaily.domain.developer.loader;

import com.andaily.domain.dto.SimpleDisabledDto;
import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Date: 13-11-26
 *
 * @author Shengzhao Li
 */
public class UserFormDtoLoader {

    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    private final String guid;
    private final String teamGuid;

    public UserFormDtoLoader(String guid, String teamGuid) {
        this.guid = guid;
        this.teamGuid = teamGuid;
    }

    public UserFormDto load() {
        UserFormDto formDto = new UserFormDto(guid, teamGuid);
        if (!formDto.isNewly()) {
            final User existUser = userRepository.findByGuid(formDto.getGuid());
            formDto = new UserFormDto(existUser);
        }
        loadTeams(formDto);
        return formDto;
    }

    private void loadTeams(UserFormDto formDto) {
        final List<SimpleDisabledDto> formDtoTeams = formDto.getTeams();
        final String teamGuid1 = formDto.getTeamGuid();
        if (StringUtils.isNotEmpty(teamGuid1)) {
            final Team team = teamRepository.findByGuid(teamGuid1);
            formDtoTeams.add(new SimpleDisabledDto(team.guid(), team.name(), false));
        } else {
            List<Team> teams = teamRepository.findAvailableTeams();
            for (Team team : teams) {
                formDtoTeams.add(new SimpleDisabledDto(team.guid(), team.name(), false));
            }
        }
    }
}
