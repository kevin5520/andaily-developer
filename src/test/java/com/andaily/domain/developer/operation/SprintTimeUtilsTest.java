package com.andaily.domain.developer.operation;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
public class SprintTimeUtilsTest {

    @Test
    public void availableTaskTimes() {
        List<String> list = SprintTimeUtils.availableTaskTimes();
        assertEquals(list.get(0), "0.5");
    }

    @Test
    public void testTaskTimeAsString() throws Exception {
        String result = SprintTimeUtils.taskTimeAsString(30);
        assertNotNull(result);
        assertEquals(result, "0.5");

        result = SprintTimeUtils.taskTimeAsString(60);
        assertNotNull(result);
        assertEquals(result, "1.0");

        result = SprintTimeUtils.taskTimeAsString(1200);
        assertNotNull(result);
        assertEquals(result, "20.0");

        result = SprintTimeUtils.taskTimeAsString(0);
        assertNotNull(result);
        assertEquals(result, "0.0");
    }
}
