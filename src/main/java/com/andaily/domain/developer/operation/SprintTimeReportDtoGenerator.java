package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.dto.developer.*;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.user.Developer;
import com.andaily.infrastructure.mybatis.data.SprintTaskTimeData;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintTimeReportDtoGenerator {


    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);

    private SprintTimeReportDto timeReportDto;

    private transient Date pageStart;
    private transient Date pageEnd;

    public SprintTimeReportDtoGenerator(SprintTimeReportDto timeReportDto) {
        this.timeReportDto = timeReportDto;
    }

    public SprintTimeReportDto generate() {
        Sprint sprint = checkingAndLoadSprint();
        loadAvailableSprints(sprint);

        initialReportDeveloperDtos(sprint);
        if (timeReportDto.isReportByDay()) {
            generateReportByDay(sprint);
        } else {
            generateReportByWeek(sprint);
        }

        return timeReportDto;
    }

    //max date period: two weeks
    private void generateReportByDay(Sprint sprint) {
        generatePageDatesByDay(sprint);

        final List<SprintTimeReportDateDto> dates = timeReportDto.getDates();
        Date tempDate = this.pageStart;
        do {
            dates.add(new SprintTimeReportDateDto(tempDate));
            generateDeveloperDayTimes(tempDate, sprint);

            tempDate = DateUtils.plusDays(tempDate, 1);
        } while (!tempDate.after(pageEnd));

        //set pagination date
        if (timeReportDto.isHasNext()) {
            timeReportDto.setNextStartDate(DateUtils.toDateText(this.pageEnd));
        }
        if (timeReportDto.isHasPrevious()) {
            timeReportDto.setPreviousEndDate(DateUtils.toDateText(this.pageStart));
        }
    }

    private void generateDeveloperDayTimes(Date date, Sprint sprint) {
        final List<SprintTimeReportDeveloperDto> reportDeveloperDtos = timeReportDto.getReportDeveloperDtos();

        BigDecimal totalDayTimes = BigDecimal.ZERO;
        final String dateAsText = DateUtils.toDateText(date);

        for (SprintTimeReportDeveloperDto reportDeveloperDto : reportDeveloperDtos) {
            final SprintTaskTimeData data = sprintRepository.sumDeveloperTaskTimes(reportDeveloperDto.getGuid(), dateAsText, sprint);
            final BigDecimal time = SprintTimeUtils.taskTimeAsBigDecimal(data.getEstimateTime());
            reportDeveloperDto.addShellTime(time, SprintTimeUtils.taskTimeAsBigDecimal(data.getActualUsedTime()));
            totalDayTimes = totalDayTimes.add(time);
        }

        final List<SprintTimeReportDateDto> totals = timeReportDto.getTotals();
        totals.add(new SprintTimeReportDateDto(totalDayTimes));
    }

    private void initialReportDeveloperDtos(Sprint sprint) {
        final List<SprintTimeReportDeveloperDto> reportDeveloperDtos = timeReportDto.getReportDeveloperDtos();
        final List<Developer> members = sprint.executeTeam().members();
        for (Developer member : members) {
            reportDeveloperDtos.add(new SprintTimeReportDeveloperDto(member));
        }
    }


    private void generatePageDatesByDay(Sprint sprint) {
        final Date startDate = sprint.startDate();
        final Date deadline = currDeadline(sprint);

        final String previousEndDate = timeReportDto.getPreviousEndDate();
        final String nextStartDate = timeReportDto.getNextStartDate();

        if (StringUtils.isNotEmpty(previousEndDate)) {
            this.pageEnd = DateUtils.plusDays(DateUtils.getDate(previousEndDate), -1);
            this.pageStart = DateUtils.plusDays(this.pageEnd, -14);
            if (!this.pageStart.after(startDate)) {
                this.pageStart = startDate;
                timeReportDto.setHasNext(true);
            } else {
                timeReportDto.setHasPrevious(true);
            }
        } else if (StringUtils.isNotEmpty(nextStartDate)) {
            this.pageStart = DateUtils.plusDays(DateUtils.getDate(nextStartDate), 1);
            this.pageEnd = DateUtils.plusDays(this.pageStart, 14);
            if (!this.pageEnd.before(deadline)) {
                this.pageEnd = deadline;
                timeReportDto.setHasPrevious(true);
            } else {
                timeReportDto.setHasNext(true);
            }
        } else {
            this.pageStart = startDate;
            this.pageEnd = DateUtils.plusDays(this.pageStart, 14);
            if (!this.pageEnd.before(deadline)) {
                this.pageEnd = deadline;
            } else {
                timeReportDto.setHasNext(true);
            }
        }
    }

    private Date currDeadline(Sprint sprint) {
        final Date currentDeadline = sprint.currentDeadline();
        if (currentDeadline != null) {
            return DateUtils.truncateDate(currentDeadline);
        }
        return sprint.deadline();
    }


    private void generateReportByWeek(Sprint sprint) {
        throw new UnsupportedOperationException("not yet implements");
    }


    private void loadAvailableSprints(Sprint sprint) {
        final Project project = sprint.project();
        List<Sprint> sprints = sprintRepository.findProjectSimpleSprints(project.guid(), SprintStatus.PENDING, SprintStatus.FINISHED);
        timeReportDto.setSprintDtos(SprintSimpleDto.toDtos(sprints));
    }

    private Sprint checkingAndLoadSprint() {
        Sprint sprint = sprintRepository.findByGuid(timeReportDto.getGuid());
        ScrumSecurityChecker.checkSprint(sprint, SprintStatus.PENDING, SprintStatus.FINISHED);
        timeReportDto.setSprint(new SprintDto(sprint));
        return sprint;
    }
}