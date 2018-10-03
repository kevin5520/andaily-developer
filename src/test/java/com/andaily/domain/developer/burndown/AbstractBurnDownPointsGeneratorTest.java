package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.shared.DateUtils;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Date: 13-9-27
 *
 * @author Shengzhao Li
 */
public class AbstractBurnDownPointsGeneratorTest extends AbstractRepositoryTest {


    @Autowired
    private SprintRepository sprintRepository;

    @Test
    public void generateActualPoints() {
        Sprint sprint = new Sprint("test", DateUtils.now(), DateUtils.now());
        sprintRepository.saveSprint(sprint);

        AbstractBurnDownPointsGenerator generator = new PendingBurnDownPointsGenerator();
        List<ActualBurnDownPoint> list = generator.generateActualPoints(sprint);

        assertTrue(list.size() == 1);
    }
}
