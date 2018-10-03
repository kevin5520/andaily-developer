package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintStatus;

/**
 * Date: 13-9-24
 *
 * @author Shengzhao Li
 */
public interface BurnDownPointsGenerator {

    boolean support(SprintStatus status);

    void generate(Sprint sprint, BurnDown burnDown);
}
