package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintStatus;

import java.util.List;

/**
 * Date: 13-9-24
 *
 * @author Shengzhao Li
 */
public class PendingBurnDownPointsGenerator extends AbstractBurnDownPointsGenerator {
    @Override
    public boolean support(SprintStatus status) {
        return SprintStatus.PENDING.equals(status);
    }

    @Override
    public void generate(Sprint sprint, BurnDown burnDown) {
        List<ExpectBurnDownPoint> expectPoints = generateExpectPoints(sprint);
        burnDown.setExpectPoints(expectPoints);

        List<ActualBurnDownPoint> actualPoints = generateActualPoints(sprint);
        burnDown.setActualPoints(actualPoints);
    }

}
