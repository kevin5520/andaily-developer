package com.andaily.domain.developer.operation;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date: 13-8-17
 *
 * @author Shengzhao Li
 */
public abstract class SprintTimeUtils {

    /**
     * The map key is the time as hour, value is the time as minute;
     */
    private static final ConcurrentHashMap<String, Integer> AVAILABLE_TASK_TIMES_MAP = new ConcurrentHashMap<>();

    /**
     * A task max time is 4h
     */
    static {
        AVAILABLE_TASK_TIMES_MAP.put("0.5", 30);
        AVAILABLE_TASK_TIMES_MAP.put("1.0", 60);
        AVAILABLE_TASK_TIMES_MAP.put("1.5", 90);
        AVAILABLE_TASK_TIMES_MAP.put("2.0", 120);
        AVAILABLE_TASK_TIMES_MAP.put("2.5", 150);
        AVAILABLE_TASK_TIMES_MAP.put("3.0", 180);
        AVAILABLE_TASK_TIMES_MAP.put("3.5", 210);
        AVAILABLE_TASK_TIMES_MAP.put("4.0", 240);
    }

    /**
     * private
     */
    private SprintTimeUtils() {
    }

    public static List<String> availableTaskTimes() {
        List<String> timeList = new ArrayList<String>(AVAILABLE_TASK_TIMES_MAP.keySet());
        Collections.sort(timeList);
        return ImmutableList.copyOf(timeList);
    }

    public static int taskTimeAsMinute(String taskTime) {
        return AVAILABLE_TASK_TIMES_MAP.get(taskTime);
    }

    /**
     * taskTime/30 = time as string;
     * taskTime should be multiple of 30
     *
     * @param taskTime taskTime
     * @return taskTime as string
     */
    public static String taskTimeAsString(int taskTime) {
        if (taskTime < 0) {
            throw new IllegalStateException("Task time should be a positive number,but it is " + taskTime);
        }
        BigDecimal tempTaskTime = taskTimeAsBigDecimal(taskTime);
        return tempTaskTime.toString();
    }


    public static BigDecimal taskTimeAsBigDecimal(int taskTime) {
        if (taskTime < 0) {
            throw new IllegalStateException("Task time should be a positive number,but it is " + taskTime);
        }
        BigDecimal taskTimeAsBigDecimal = new BigDecimal(taskTime);
        return taskTimeAsBigDecimal.divide(new BigDecimal("60"), 1, BigDecimal.ROUND_UP);
    }

}
