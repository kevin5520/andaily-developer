package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.SprintTaskStatus;
import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.shared.grouper.GroupResult;
import com.andaily.domain.shared.grouper.impl.SprintSimpleDtoStatusGrouper;
import com.andaily.domain.shared.paginated.DefaultPaginated;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 */
public class DeveloperOverviewDto extends DefaultPaginated<SprintTaskDto> {

    private SprintDto currentSprint;
    private List<SprintSimpleDto> sprintDtos = new ArrayList<>();

    private SprintTaskStatus status = SprintTaskStatus.PENDING;

    private int totalCreatedTasks;
    private int totalPendingTasks;
    private int totalCanceledTasks;
    private int totalFinishedTasks;

    private String totalEstimatedHours;
    private String totalUsedHours;

    private BurnDownChartWrapper burnDownWrapper;
    private List<SprintMeetingDto> latestMeetings = new ArrayList<>();

    private String number;
    private boolean onlyShowMyTasks;

    public DeveloperOverviewDto() {
    }

    @Override
    public void afterLoad() {
        int totalEstTime = 0;
        int totalUsedTime = 0;
        for (SprintTaskDto sprintTaskDto : list) {
            totalEstTime += sprintTaskDto.getEstimateTimeAsInt();
            totalUsedTime += sprintTaskDto.getActualUsedTimeAsInt();
        }
        this.totalEstimatedHours = SprintTimeUtils.taskTimeAsString(totalEstTime);
        this.totalUsedHours = SprintTimeUtils.taskTimeAsString(totalUsedTime);
    }

    public List<GroupResult<SprintStatus, SprintSimpleDto>> getSprintDtosGroupResults() {
        SprintSimpleDtoStatusGrouper grouper = new SprintSimpleDtoStatusGrouper(sprintDtos);
        return grouper.group().getGroupResults();
    }

    public Map<String, Object> queryParams() {
        Map<String, Object> map = super.defaultQueryMap();
        map.put("status", status);
        map.put("onlyShowMyTasks", onlyShowMyTasks);
        map.put("number", StringUtils.isEmpty(number) ? null : number);
        map.put("sprintGuid", currentSprint != null ? currentSprint.getGuid() : null);
        // Different role will call different limit
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        map.put("isProductOwner", scrumTerm.equals(ScrumTerm.PRODUCT_OWNER) ? "yes" : null);
        map.put("isTeamMember", (scrumTerm.isMaster() || scrumTerm.isMember()) ? "yes" : null);

        map.put("orderBy", SprintTaskStatus.FINISHED.equals(status) ? "a.finish_time" : "a.number_");
        return map;
    }

    public boolean isOnlyShowMyTasks() {
        return onlyShowMyTasks;
    }

    public void setOnlyShowMyTasks(boolean onlyShowMyTasks) {
        this.onlyShowMyTasks = onlyShowMyTasks;
    }

    public int getTotalTasks() {
        return totalCreatedTasks + totalCanceledTasks + totalFinishedTasks + totalPendingTasks;
    }

    public int getTotalCreatedTasks() {
        return totalCreatedTasks;
    }

    public List<SprintTaskStatus> getStatuses() {
        return Arrays.asList(SprintTaskStatus.CREATED,
                SprintTaskStatus.PENDING,
                SprintTaskStatus.CANCELED,
                SprintTaskStatus.FINISHED);
    }

    /**
     * Current sprint is finished or not
     *
     * @return True is finished, otherwise false
     */
    public boolean isCurrSprintFinished() {
        return currentSprint != null && currentSprint.getStatus().isFinished();
    }

    public BurnDownChartWrapper getBurnDownWrapper() {
        return burnDownWrapper;
    }

    public void setBurnDownWrapper(BurnDownChartWrapper burnDownWrapper) {
        this.burnDownWrapper = burnDownWrapper;
    }

    public void setTotalCreatedTasks(int totalCreatedTasks) {
        this.totalCreatedTasks = totalCreatedTasks;
    }

    public int getTotalPendingTasks() {
        return totalPendingTasks;
    }

    public void setTotalPendingTasks(int totalPendingTasks) {
        this.totalPendingTasks = totalPendingTasks;
    }

    public int getTotalCanceledTasks() {
        return totalCanceledTasks;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTotalCanceledTasks(int totalCanceledTasks) {
        this.totalCanceledTasks = totalCanceledTasks;
    }

    public int getTotalFinishedTasks() {
        return totalFinishedTasks;
    }

    public void setTotalFinishedTasks(int totalFinishedTasks) {
        this.totalFinishedTasks = totalFinishedTasks;
    }

    public SprintDto getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(SprintDto currentSprint) {
        this.currentSprint = currentSprint;
    }

    public List<SprintSimpleDto> getSprintDtos() {
        return sprintDtos;
    }

    public void setSprintDtos(List<SprintSimpleDto> sprintDtos) {
        this.sprintDtos = sprintDtos;
    }

    public SprintTaskStatus getStatus() {
        return status;
    }

    public void setStatus(SprintTaskStatus status) {
        this.status = status;
    }

    public String getTotalEstimatedHours() {
        return totalEstimatedHours;
    }

    public void setTotalEstimatedHours(String totalEstimatedHours) {
        this.totalEstimatedHours = totalEstimatedHours;
    }

    public String getTotalUsedHours() {
        return totalUsedHours;
    }

    public void setTotalUsedHours(String totalUsedHours) {
        this.totalUsedHours = totalUsedHours;
    }

    public List<SprintMeetingDto> getLatestMeetings() {
        return latestMeetings;
    }

    public void setLatestMeetings(List<SprintMeetingDto> latestMeetings) {
        this.latestMeetings = latestMeetings;
    }
}
