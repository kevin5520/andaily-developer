package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.SprintPriority;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.SprintTaskStatus;
import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.MessageUtils;
import com.andaily.infrastructure.support.st.STRender;

import java.util.*;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 */
public class SprintTaskDto extends AbstractDTO {

    public static final String LARGER_THAN_4_HOURS = "sprint.task.larger.than.4.hours";

    protected String number;
    protected String name;
    protected String estimateTime;
    protected String actualUsedTime;

    protected int estimateTimeAsInt;
    protected int actualUsedTimeAsInt;

    protected SprintTaskStatus status = SprintTaskStatus.CREATED;
    protected String pendingTime;
    protected String finishTime;
    protected String createTime;

    protected String cancelTime;
    protected SprintPriority priority = SprintPriority.DEFAULT;
    protected boolean urgent;

    protected String description;
    protected int timeDifference;

    protected boolean referBacklog;
    protected boolean moved;

    protected String finishComment;
    protected boolean largerMaxTime;

    protected DeveloperDto executor;
    protected DeveloperDto cancelUser;
    protected DeveloperDto creator;

    protected boolean executorOfMine;

    public SprintTaskDto() {
    }

    public SprintTaskDto(SprintTask task) {
        super(task.guid());
        this.createTime = DateUtils.toDateTime(task.createTime());
        this.name = task.name();
        this.estimateTime = task.estimateTimeAsHour();
        this.actualUsedTime = task.actualUsedTimeAsHour();
        this.timeDifference = task.timeDifference();

        this.estimateTimeAsInt = task.estimateTime();
        this.actualUsedTimeAsInt = task.actualUsedTime();
        this.creator = new DeveloperDto((Developer) task.creator());

        this.status = task.status();
        this.urgent = task.urgent();
        this.priority = task.priority();
        this.number = task.number();

        this.description = task.description();
        Backlog backlog = task.backlog();
        this.referBacklog = (backlog != null);
        this.moved = task.moved();

        Date pTime = task.pendingTime();
        if (pTime != null) {
            this.pendingTime = DateUtils.toDateTime(pTime);
        }
        Date cTime = task.cancelTime();
        if (cTime != null) {
            this.cancelTime = DateUtils.toDateTime(cTime);
        }
        Date fTime = task.finishTime();
        if (fTime != null) {
            this.finishTime = DateUtils.toDateTime(fTime);
        }
        this.finishComment = task.finishComment();
        this.largerMaxTime = task.largerMaxTime();

        final User executor1 = task.executor();
        if (executor1 != null) {
            this.executor = new DeveloperDto((Developer) executor1);
            this.executorOfMine = SecurityUtils.currUser().equals(executor1);
        }

        final User user = task.cancelUser();
        if (user != null) {
            this.cancelUser = new DeveloperDto((Developer) user);
        }
    }

    public boolean isCanDoTask() {
        return this.executorOfMine || this.executor == null;
    }

    public DeveloperDto getExecutor() {
        return executor;
    }

    public DeveloperDto getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(DeveloperDto cancelUser) {
        this.cancelUser = cancelUser;
    }

    public void setExecutor(DeveloperDto executor) {
        this.executor = executor;
    }

    public String getFinishComment() {
        return finishComment;
    }

    public void setFinishComment(String finishComment) {
        this.finishComment = finishComment;
    }

    public DeveloperDto getCreator() {
        return creator;
    }

    public boolean isExecutorOfMine() {
        return executorOfMine;
    }

    public static List<SprintTaskDto> toDtos(List<SprintTask> tasks) {
        List<SprintTaskDto> taskDtos = new ArrayList<>(tasks.size());
        for (SprintTask task : tasks) {
            taskDtos.add(new SprintTaskDto(task));
        }
        return taskDtos;
    }

    public boolean isReferBacklog() {
        return referBacklog;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public void setReferBacklog(boolean referBacklog) {
        this.referBacklog = referBacklog;
    }

    //Html style tooltip
    public String getTooltip() {
        Map<String, Object> model = new HashMap<>();
        model.put("createTime", createTime);
        model.put("creator", creator.getShowName());
        model.put("estimateTime", estimateTime);
        model.put("priority", priority.getLabel());

        model.put("isPending", SprintTaskStatus.PENDING.equals(status));
        model.put("isCanceled", SprintTaskStatus.CANCELED.equals(status));
        model.put("isFinished", SprintTaskStatus.FINISHED.equals(status));

        model.put("pendingTime", pendingTime);
        model.put("cancelTime", cancelTime);
        model.put("finishTime", finishTime);

        model.put("actualUsedTime", actualUsedTime);
        model.put("timeDifferenceAsHtml", getTimeDifferenceAsHtml());
        model.put("urgent", urgent);
        model.put("description", description);

        model.put("finishComment", finishComment);
        model.put("cancelUser", this.cancelUser != null ? this.cancelUser.getShowName() : "");

        final boolean chinese = SecurityUtils.currUser().language().isChinese();
        STRender stRender = new STRender(chinese ? "template/sprint_task_tip_cn.html" : "template/sprint_task_tip.html", model);
        return stRender.render();
    }

    //Only use if the task status is FINISHED
    public String getTimeDifferenceAsHtml() {
        if (!SprintTaskStatus.FINISHED.equals(this.status)) {
            return "";
        }
        StringBuilder text = new StringBuilder();
        final String largerThanMessage = MessageUtils.getMessage(LARGER_THAN_4_HOURS);
        if (largerMaxTime) {
            text.append("(<span class='text-error' title='").append(largerThanMessage).append("'>&gt; 4</span>)");
        } else if (timeDifference > 0) {
            text.append("(<span class='blue'>-").append(SprintTimeUtils.taskTimeAsString(timeDifference)).append("</span>)");
        } else if (timeDifference < 0) {
            text.append("(<span class='red'>+").append(SprintTimeUtils.taskTimeAsString(-timeDifference)).append("</span>)");
        }
        return text.toString();
    }

    public int getTimeDifference() {
        return timeDifference;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getActualUsedTime() {
        return actualUsedTime;
    }

    public void setActualUsedTime(String actualUsedTime) {
        this.actualUsedTime = actualUsedTime;
    }

    public SprintTaskStatus getStatus() {
        return status;
    }

    public void setStatus(SprintTaskStatus status) {
        this.status = status;
    }

    public String getPendingTime() {
        return pendingTime;
    }

    public void setPendingTime(String pendingTime) {
        this.pendingTime = pendingTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public SprintPriority getPriority() {
        return priority;
    }

    public void setPriority(SprintPriority priority) {
        this.priority = priority;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isLargerMaxTime() {
        return largerMaxTime;
    }

    public void setLargerMaxTime(boolean largerMaxTime) {
        this.largerMaxTime = largerMaxTime;
    }

    public int getEstimateTimeAsInt() {
        return estimateTimeAsInt;
    }

    public void setEstimateTimeAsInt(int estimateTimeAsInt) {
        this.estimateTimeAsInt = estimateTimeAsInt;
    }

    public int getActualUsedTimeAsInt() {
        return actualUsedTimeAsInt;
    }

    public void setActualUsedTimeAsInt(int actualUsedTimeAsInt) {
        this.actualUsedTimeAsInt = actualUsedTimeAsInt;
    }
}
