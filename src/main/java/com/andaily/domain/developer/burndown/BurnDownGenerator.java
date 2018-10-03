package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-9-24
 *
 * @author Shengzhao Li
 */
public class BurnDownGenerator {

    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private Sprint sprint;
    private List<BurnDownPointsGenerator> burnDownPointsGenerators = new ArrayList<BurnDownPointsGenerator>();

    public BurnDownGenerator(Sprint sprint) {
        this.sprint = sprint;
        initialPointsGenerators();
    }

    private void initialPointsGenerators() {
        burnDownPointsGenerators.add(new CreatedBurnDownPointsGenerator());
        burnDownPointsGenerators.add(new PendingBurnDownPointsGenerator());
        burnDownPointsGenerators.add(new FinishedBurnDownPointsGenerator());
    }

    public BurnDown generate() {
        BurnDown burnDown = new BurnDown(sprint);
        generateLabels(burnDown);
        generatePoints(burnDown);
        return burnDown;
    }

    private void generateLabels(BurnDown burnDown) {
        Date lastDate = resolveLastDate();
        Date tempStart = sprint.startDate();
        List<BurnDownLabel> labels = new ArrayList<BurnDownLabel>();
        do {
            BurnDownLabel label = new BurnDownLabel(tempStart);
            labels.add(label);
            tempStart = org.apache.commons.lang.time.DateUtils.addDays(tempStart, 1);
        } while (!tempStart.after(lastDate));

        burnDown.setLabels(labels);
    }

    private Date resolveLastDate() {
        Date lastDate = sprintRepository.findSprintLastTaskTime(sprint);
        Date deadline = sprint.deadline();
        if (lastDate == null) {
            lastDate = deadline;
        } else {
            if (lastDate.before(deadline)) {
                lastDate = deadline;
            }
        }
        return lastDate;
    }

    private void generatePoints(BurnDown burnDown) {
        SprintStatus status = sprint.status();
        for (BurnDownPointsGenerator burnDownPointsGenerator : burnDownPointsGenerators) {
            if (burnDownPointsGenerator.support(status)) {
                burnDownPointsGenerator.generate(sprint, burnDown);
            }
        }

    }
}
