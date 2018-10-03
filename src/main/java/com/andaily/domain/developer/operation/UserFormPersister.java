package com.andaily.domain.developer.operation;

import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

/**
 * Date: 13-12-3
 *
 * @author Shengzhao Li
 */
public class UserFormPersister {

    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);
    private transient TeamRepository teamRepository = BeanProvider.getBean(TeamRepository.class);

    private UserFormDto userFormDto;

    public UserFormPersister(UserFormDto userFormDto) {
        this.userFormDto = userFormDto;
    }

    public String persist() {
        if (userFormDto.isNewly()) {
            return saveUser();
        } else {
            return updateUser();
        }
    }

    private String updateUser() {
        final Developer existDeveloper = developerRepository.findByGuid(userFormDto.getGuid());
        existDeveloper.updateScrumTerm(userFormDto.getScrumTerm())
                .email(userFormDto.getEmail())
                .cellPhone(userFormDto.getCellPhone())
                .description(userFormDto.getDescription())
                .nickName(userFormDto.getNickName());
        updateTeam(existDeveloper);

        existDeveloper.saveOrUpdate();
        return existDeveloper.guid();
    }

    private String saveUser() {
        Developer developer = userFormDto.toDomain();
        updateTeam(developer);

        developer.saveOrUpdate();
        return developer.guid();
    }

    private void updateTeam(Developer developer) {
        final String teamGuid = userFormDto.getTeamGuid();
        if (StringUtils.isNotEmpty(teamGuid)) {
            developer.team(teamRepository.findByGuid(teamGuid));
        }
    }
}
