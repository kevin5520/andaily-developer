package com.andaily.domain.developer.burndown;

import com.andaily.ContextTest;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.shared.DateUtils;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Date: 13-9-26
 *
 * @author Shengzhao Li
 */
public class CreatedBurnDownPointsGeneratorTest extends ContextTest {


    @Test
    public void testGenerate() throws Exception {
        CreatedBurnDownPointsGenerator generator = new CreatedBurnDownPointsGenerator();

        Date start = DateUtils.getDate("2013-09-01", DateUtils.DATE_FORMAT);
        Date deadline = DateUtils.getDate("2013-09-10", DateUtils.DATE_FORMAT);
        Sprint sprint = new Sprint("test", start, deadline) {
            public int estimateTimes() {
                return 480;
            }
        };

        BurnDown burnDown = new BurnDown();
        generator.generate(sprint, burnDown);

        List<ExpectBurnDownPoint> expectPoints = burnDown.getExpectPoints();
        assertTrue(expectPoints.size() == 10);
        assertEquals(expectPoints.get(9).getExpectRemainTime().intValue(), 0);
        System.out.println(expectPoints);
    }
}
