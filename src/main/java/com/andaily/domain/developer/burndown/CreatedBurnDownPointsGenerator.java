package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintStatus;

import java.util.List;

/**
 * Date: 13-9-24
 * <p/>
 * Just have expect points
 *
 * @author Shengzhao Li
 */
public class CreatedBurnDownPointsGenerator extends AbstractBurnDownPointsGenerator {
    @Override
    public boolean support(SprintStatus status) {
        return SprintStatus.CREATED.equals(status);
    }

    @Override
    public void generate(Sprint sprint, BurnDown burnDown) {
        List<ExpectBurnDownPoint> expectPoints = generateExpectPoints(sprint);
        burnDown.setExpectPoints(expectPoints);
    }

}
