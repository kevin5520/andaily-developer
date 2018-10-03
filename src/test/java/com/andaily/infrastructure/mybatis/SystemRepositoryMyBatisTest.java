package com.andaily.infrastructure.mybatis;

import com.andaily.domain.shared.system.SystemConfiguration;
import com.andaily.domain.shared.system.SystemRepository;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;

/**
 * @author Shengzhao Li
 */
public class SystemRepositoryMyBatisTest extends AbstractRepositoryTest {


    @Autowired
    private SystemRepository systemRepository;


    @Test
    public void testSystemConfiguration() {
        final SystemConfiguration configuration = systemRepository.findSystemConfiguration();
        assertNull(configuration);

    }

}