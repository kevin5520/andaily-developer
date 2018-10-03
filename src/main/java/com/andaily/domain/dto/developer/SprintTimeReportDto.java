package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.shared.grouper.GroupResult;
import com.andaily.domain.shared.grouper.impl.SprintSimpleDtoStatusGrouper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintTimeReportDto extends AbstractDTO {

    private SprintDto sprint;
    private List<SprintSimpleDto> sprintDtos = new ArrayList<>();

    private boolean reportByDay = true;

    private List<SprintTimeReportDateDto> dates = new ArrayList<>();
    private List<SprintTimeReportDeveloperDto> reportDeveloperDtos = new ArrayList<>();
    private List<SprintTimeReportDateDto> totals = new ArrayList<>();


    //pagination
    private boolean hasNext;
    private String nextStartDate;

    private boolean hasPrevious;
    private String previousEndDate;


    public SprintTimeReportDto() {
    }


    public List<GroupResult<SprintStatus, SprintSimpleDto>> getSprintDtosGroupResults() {
        SprintSimpleDtoStatusGrouper grouper = new SprintSimpleDtoStatusGrouper(sprintDtos);
        return grouper.group().getGroupResults();
    }

    public SprintDto getSprint() {
        return sprint;
    }

    public void setSprint(SprintDto sprint) {
        this.sprint = sprint;
    }

    public List<SprintSimpleDto> getSprintDtos() {
        return sprintDtos;
    }

    public void setSprintDtos(List<SprintSimpleDto> sprintDtos) {
        this.sprintDtos = sprintDtos;
    }

    public boolean isReportByDay() {
        return reportByDay;
    }

    public void setReportByDay(boolean reportByDay) {
        this.reportByDay = reportByDay;
    }

    public List<SprintTimeReportDateDto> getDates() {
        return dates;
    }

    public void setDates(List<SprintTimeReportDateDto> dates) {
        this.dates = dates;
    }

    public List<SprintTimeReportDeveloperDto> getReportDeveloperDtos() {
        return reportDeveloperDtos;
    }

    public void setReportDeveloperDtos(List<SprintTimeReportDeveloperDto> reportDeveloperDtos) {
        this.reportDeveloperDtos = reportDeveloperDtos;
    }

    public List<SprintTimeReportDateDto> getTotals() {
        return totals;
    }

    public void setTotals(List<SprintTimeReportDateDto> totals) {
        this.totals = totals;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getNextStartDate() {
        return nextStartDate;
    }

    public void setNextStartDate(String nextStartDate) {
        this.nextStartDate = nextStartDate;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public String getPreviousEndDate() {
        return previousEndDate;
    }

    public void setPreviousEndDate(String previousEndDate) {
        this.previousEndDate = previousEndDate;
    }

    public BigDecimal getAllTotalTimes() {
        BigDecimal totalTimes = BigDecimal.ZERO;
        for (SprintTimeReportDateDto total : totals) {
            totalTimes = totalTimes.add(total.getTimes());
        }
        return totalTimes;
    }

}