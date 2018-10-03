package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.log.Log;
import com.andaily.domain.log.LogRepository;
import com.andaily.domain.log.SprintActivityLog;
import com.andaily.domain.shared.GuidGenerator;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * @author Shengzhao Li
 */
public class LogRepositoryMyBatisTest extends AbstractRepositoryTest {


    @Autowired
    private LogRepository logRepository;

    @Test
    public void findByGuid() {
        final Log log = logRepository.findByGuid(GuidGenerator.generate());
        assertNull(log);

        SprintActivityLog activityLog = new SprintActivityLog();
        activityLog.saveOrUpdate();

        final Log log1 = logRepository.findByGuid(activityLog.guid());
        assertNotNull(log1);

    }


    @Test
    public void findSprintActivityLogs() {
        Map<String, Object> map = new HashMap<>();
        map.put("currUser", SecurityUtils.currUser());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("sprintGuid", GuidGenerator.generate());

        final List<SprintActivityLog> list = logRepository.findSprintActivityLogs(map);
        assertEquals(list.size(), 0);

        final int i = logRepository.totalSprintActivityLogs(map);
        assertEquals(i, 0);

    }
}