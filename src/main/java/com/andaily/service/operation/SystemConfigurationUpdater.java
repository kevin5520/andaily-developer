package com.andaily.service.operation;

import com.andaily.domain.dto.user.SystemConfigurationFormDto;
import com.andaily.domain.shared.Application;
import com.andaily.domain.shared.system.SystemConfiguration;
import com.andaily.domain.shared.system.SystemRepository;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class SystemConfigurationUpdater {

    private static final LogHelper LOG = LogHelper.create(SystemConfigurationUpdater.class);

    private transient SystemRepository systemRepository = BeanProvider.getBean(SystemRepository.class);
    private SystemConfigurationFormDto formDto;

    public SystemConfigurationUpdater(SystemConfigurationFormDto formDto) {
        this.formDto = formDto;
    }

    public void update() {
        final SystemConfiguration systemConfiguration = systemRepository.findSystemConfiguration();
        formDto.updateDomain(systemConfiguration);
        systemConfiguration.saveOrUpdate();

        syncUpdateToApplication(systemConfiguration);

        LOG.debug("Update SystemConfiguration: " + systemConfiguration);
    }

    private void syncUpdateToApplication(SystemConfiguration newSystemConfiguration) {
        Application.systemConfiguration().perPageSize(newSystemConfiguration.perPageSize());
    }
}