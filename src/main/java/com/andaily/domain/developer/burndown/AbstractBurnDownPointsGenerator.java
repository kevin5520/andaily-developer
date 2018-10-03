package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.shared.DateUtils;
import com.andaily.web.context.BeanProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date: 13-9-24
 *
 * @author Shengzhao Li
 */
public abstract class AbstractBurnDownPointsGenerator implements BurnDownPointsGenerator {

    protected transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    //include deadline day
    protected long period(Sprint sprint) {
        Date start = sprint.startDate();
        Date deadline = sprint.deadline();
        return DateUtils.periodAsDays(start, deadline);
    }

    protected Date resolveEndDate(Sprint sprint) {
        SprintStatus status = sprint.status();
        Date end;
        switch (status) {
            case PENDING:
            case FINISHED:
                end = sprintRepository.findSprintLastTaskTime(sprint);
                if (end == null) {
                    end = sprint.startDate();
                }
                break;
            default:
                end = sprint.deadline();
        }
        return end;
    }

    protected List<ExpectBurnDownPoint> generateExpectPoints(Sprint sprint) {
        Date deadline = sprint.deadline();
        long period = period(sprint);

        final BigDecimal estimateHours = estimateTimeAsBigDecimal(sprint);
        BigDecimal perDayHours = estimateHours.divide(new BigDecimal(period), 4, BigDecimal.ROUND_HALF_EVEN);

        List<ExpectBurnDownPoint> expectBurnDownPoints = new ArrayList<ExpectBurnDownPoint>();
        BigDecimal tempRemainHours = estimateHours;
        Date tempDate = sprint.startDate();
        do {
            ExpectBurnDownPoint point = new ExpectBurnDownPoint(tempDate, tempRemainHours.setScale(2, RoundingMode.HALF_EVEN));
            expectBurnDownPoints.add(point);

            tempDate = org.apache.commons.lang.time.DateUtils.addDays(tempDate, 1);
            tempRemainHours = tempRemainHours.subtract(perDayHours);

        } while (!tempDate.after(deadline));
        return expectBurnDownPoints;
    }

    protected BigDecimal estimateTimeAsBigDecimal(Sprint sprint) {
        String estHours = SprintTimeUtils.taskTimeAsString(sprint.estimateTimes());
        return new BigDecimal(estHours);
    }

    protected List<ActualBurnDownPoint> generateActualPoints(Sprint sprint) {
        final Date start = sprint.startDate();
        final Date end = resolveEndDate(sprint);

        List<ActualBurnDownPoint> actualPoints = new ArrayList<ActualBurnDownPoint>();
        BigDecimal tempRemainHours = estimateTimeAsBigDecimal(sprint);
        Date tempDate = start;
        do {
            int times = sprintRepository.findSpecifyDayFinishedTaskTimes(sprint, tempDate);
            BigDecimal dayFinishedTime = new BigDecimal(SprintTimeUtils.taskTimeAsString(times));

            ActualBurnDownPoint point = new ActualBurnDownPoint(tempDate, tempRemainHours.setScale(2, RoundingMode.HALF_EVEN));
            point.setDateFinishedTime(dayFinishedTime.setScale(2, RoundingMode.HALF_EVEN));
            actualPoints.add(point);

            tempDate = org.apache.commons.lang.time.DateUtils.addDays(tempDate, 1);
            tempRemainHours = tempRemainHours.subtract(dayFinishedTime);

        } while (!tempDate.after(end));
        return actualPoints;
    }
}
