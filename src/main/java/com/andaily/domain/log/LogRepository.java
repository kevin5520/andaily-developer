package com.andaily.domain.log;

import com.andaily.domain.shared.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */

public interface LogRepository extends Repository {


    Log findByGuid(String guid);

    void saveSprintActivityLog(SprintActivityLog log);

    List<SprintActivityLog> findSprintActivityLogs(Map<String, Object> map);

    int totalSprintActivityLogs(Map<String, Object> map);
}