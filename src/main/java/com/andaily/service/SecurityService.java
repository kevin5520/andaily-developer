package com.andaily.service;

import com.andaily.domain.dto.user.SystemConfigurationFormDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Shengzhao Li
 */

public interface SecurityService extends UserDetailsService {

    SystemConfigurationFormDto loadSystemConfigurationFormDto();

    void updateSystemConfiguration(SystemConfigurationFormDto formDto);
}