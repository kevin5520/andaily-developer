package com.andaily.service.impl;

import com.andaily.domain.dto.user.AndailyUserDetails;
import com.andaily.domain.dto.user.SystemConfigurationFormDto;
import com.andaily.domain.shared.system.SystemConfiguration;
import com.andaily.domain.shared.system.SystemRepository;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.service.SecurityService;
import com.andaily.service.operation.SystemConfigurationUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Shengzhao Li
 */
public class SecurityServiceImpl implements SecurityService {

    private static LogHelper log = LogHelper.create(SecurityServiceImpl.class);

    private UserRepository userRepository;

    private SystemRepository systemRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by username: " + username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found any LoginUser by [" + username + "] or it is disabled");
        }
        return new AndailyUserDetails(user);
    }


    @Override
    public SystemConfigurationFormDto loadSystemConfigurationFormDto() {
        final SystemConfiguration configuration = systemRepository.findSystemConfiguration();
        return new SystemConfigurationFormDto(configuration);
    }

    @Override
    public void updateSystemConfiguration(SystemConfigurationFormDto formDto) {
        SystemConfigurationUpdater updater = new SystemConfigurationUpdater(formDto);
        updater.update();
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSystemRepository(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }
}
