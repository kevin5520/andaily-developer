package com.andaily.domain.shared.system;

import com.andaily.domain.shared.Repository;

/**
 * @author Shengzhao Li
 */

public interface SystemRepository extends Repository {

    SystemConfiguration findSystemConfiguration();

    void updateSystemConfiguration(SystemConfiguration systemConfiguration);

}