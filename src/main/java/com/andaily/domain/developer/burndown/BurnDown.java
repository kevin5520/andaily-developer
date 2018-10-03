package com.andaily.domain.developer.burndown;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.operation.SprintTimeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-9-24
 * <p/>
 * Define a sprint burn down
 *
 * @author Shengzhao Li
 */
public class BurnDown implements Serializable {

    private String sprintGuid;
    private String sprintName;

    //unit: minute
    private int estimateTimes;
    //unit minute
    private int usedTimes;

    private List<BurnDownLabel> labels = new ArrayList<>();

    private List<ExpectBurnDownPoint> expectPoints = new ArrayList<>();
    private List<ActualBurnDownPoint> actualPoints = new ArrayList<>();

    public BurnDown() {
    }

    /**
     * Should be call {@link BurnDownGenerator} to generate <code>BurnDown</code> instance.
     *
     * @param sprint Sprint
     */
    public BurnDown(Sprint sprint) {
        this.sprintGuid = sprint.guid();
        this.sprintName = sprint.name();

        this.usedTimes = sprint.usedTimes();
        this.estimateTimes = sprint.estimateTimes();
    }

    public String getSprintGuid() {
        return sprintGuid;
    }

    public String getSprintName() {
        return sprintName;
    }

    public int getEstimateTimes() {
        return estimateTimes;
    }

    public String getEstimateTimesAsHours() {
        return SprintTimeUtils.taskTimeAsString(estimateTimes);
    }


    public int getUsedTimes() {
        return usedTimes;
    }

    public String getUsedTimesAsHours() {
        return SprintTimeUtils.taskTimeAsString(usedTimes);
    }


    public List<ExpectBurnDownPoint> getExpectPoints() {
        return expectPoints;
    }

    public List<ActualBurnDownPoint> getActualPoints() {
        return actualPoints;
    }

    public BurnDown setExpectPoints(List<ExpectBurnDownPoint> expectPoints) {
        this.expectPoints = expectPoints;
        return this;
    }

    public BurnDown setActualPoints(List<ActualBurnDownPoint> actualPoints) {
        this.actualPoints = actualPoints;
        return this;
    }

    public List<BurnDownLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<BurnDownLabel> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "{" +
                "usedTimes=" + usedTimes +
                ", estimateTimes=" + estimateTimes +
                ", sprintName='" + sprintName + '\'' +
                ", sprintGuid='" + sprintGuid + '\'' +
                '}';
    }
}
