package com.andaily.domain.dto.user;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.shared.system.SystemConfiguration;

import static com.andaily.domain.shared.system.SystemConfiguration.PerPageSize;

/**
 * @author Shengzhao Li
 */
public class SystemConfigurationDto extends AbstractDTO {


    protected PerPageSize perPageSize = PerPageSize.TWENTY;


    public SystemConfigurationDto() {
    }

    public SystemConfigurationDto(SystemConfiguration configuration) {
        super(configuration.guid());
        this.perPageSize = configuration.perPageSize();
    }

    public PerPageSize getPerPageSize() {
        return perPageSize;
    }

    public void setPerPageSize(PerPageSize perPageSize) {
        this.perPageSize = perPageSize;
    }
}