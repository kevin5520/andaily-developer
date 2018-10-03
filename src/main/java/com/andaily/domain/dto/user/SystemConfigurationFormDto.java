package com.andaily.domain.dto.user;

import com.andaily.domain.shared.system.SystemConfiguration;

import static com.andaily.domain.shared.system.SystemConfiguration.PerPageSize;

/**
 * @author Shengzhao Li
 */
public class SystemConfigurationFormDto extends SystemConfigurationDto {


    public SystemConfigurationFormDto() {
        super();
    }

    public SystemConfigurationFormDto(SystemConfiguration configuration) {
        super(configuration);
    }


    public PerPageSize[] getPerPageSizes() {
        return PerPageSize.values();
    }

    public SystemConfiguration updateDomain(SystemConfiguration configuration) {
        return configuration.perPageSize(perPageSize);
    }
}