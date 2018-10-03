package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.developer.operation.ScrumSecurityChecker;
import com.andaily.domain.developer.operation.SprintTimeUtils;
import com.andaily.domain.dto.developer.FinishSprintTaskDto;
import com.andaily.domain.dto.developer.SprintTaskCommentDto;
import com.andaily.domain.dto.developer.StartTaskCheckingResult;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

import java.util.Date;

/**
 * Date: 13-8-3
 *
 * @author Shengzhao Li
 */
public class SprintTask extends AbstractDomain {

    private static LogHelper log = LogHelper.create(SprintTask.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private String name;
    private User creator;
    //unique in the project
    private int number;
    /**
     * The task estimate  time (unit: minute)
     */
    private int estimateTime;

    /**
     * The task actual used time (unit: minute)
     */
    private int actualUsedTime;
    /**
     * Label the task actual used time larger than the max specify time(default 4 hours).
     */
    private boolean largerMaxTime = false;

    private Sprint sprint;
    //Default status is Created
    private SprintTaskStatus status = SprintTaskStatus.CREATED;

    private Date pendingTime;
    private User executor;

    private Date finishTime;
    //A comment of user when finish task if the user want to input; it is optional
    private String finishComment;

    private Date cancelTime;
    private User cancelUser;

    private SprintPriority priority = SprintPriority.DEFAULT;
    //If this is an urgent task, the value is true
    private boolean urgent;
    /**
     * null is available, If it is not null ,
     * the backlog should be belong to {@link Sprint#backlogs} .
     */
    private Backlog backlog;

    private String description;

    public SprintTask() {
    }

    public SprintTask(String name, int estimateTime, SprintPriority priority, boolean urgent) {
        this.name = name;
        this.estimateTime = estimateTime;
        this.priority = priority;
        this.urgent = urgent;
    }

    public SprintTask updateSprint(Sprint sprint) {
        this.sprint = sprint;
        return this;
    }

    public String number() {
        String tempText = "00000" + this.number;
        int length = tempText.length();
        return tempText.substring(length - 5, length);
    }

    public SprintTask number(int number) {
        this.number = number;
        return this;
    }

    public String name() {
        return name;
    }

    public User creator() {
        return creator;
    }

    public int estimateTime() {
        return estimateTime;
    }

    public int actualUsedTime() {
        return actualUsedTime;
    }

    public Sprint sprint() {
        return sprint;
    }

    public SprintTaskStatus status() {
        return status;
    }

    public Date pendingTime() {
        return pendingTime;
    }

    public User executor() {
        return executor;
    }

    public Date finishTime() {
        return finishTime;
    }

    public Date cancelTime() {
        return cancelTime;
    }

    public User cancelUser() {
        return cancelUser;
    }

    public SprintPriority priority() {
        return priority;
    }

    public boolean urgent() {
        return urgent;
    }

    public Backlog backlog() {
        return backlog;
    }

    public SprintTask updateCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public SprintTask updateName(String name) {
        this.name = name;
        return this;
    }

    public String description() {
        return description;
    }

    public String actualUsedTimeAsHour() {
        return SprintTimeUtils.taskTimeAsString(actualUsedTime);
    }

    /**
     * Return <i>estimateTime - actualUsedTime</i>.
     *
     * @return time difference
     */
    public int timeDifference() {
        return estimateTime - actualUsedTime;
    }

    public String estimateTimeAsHour() {
        return SprintTimeUtils.taskTimeAsString(estimateTime);
    }

    public SprintTask updateBacklog(Backlog backlog) {
        this.backlog = backlog;
        return this;
    }

    public SprintTask updateDescription(String description) {
        this.description = description;
        return this;
    }

    public SprintTask updatePriority(SprintPriority priority) {
        this.priority = priority;
        return this;
    }

    public SprintTask updateUrgent(boolean urgent) {
        this.urgent = urgent;
        return this;
    }

    public SprintTask updateEstimateTime(int estTime) {
        this.estimateTime = estTime;
        return this;
    }

    /**
     * When update status, update the reference time too;
     * E.g.   update status to PENDING, then set pendingTime is now().
     *
     * @param status SprintTaskStatus
     * @return SprintTask
     */
    public SprintTask updateStatus(SprintTaskStatus status) {
        this.status = status;
        if (SprintTaskStatus.PENDING.equals(status)) {
            this.pendingTime = DateUtils.now();
        } else if (SprintTaskStatus.CANCELED.equals(status)) {
            this.cancelTime = DateUtils.now();
            this.cancelUser = SecurityUtils.currUser();
        } else if (SprintTaskStatus.FINISHED.equals(status)) {
            this.finishTime = DateUtils.now();
        }
        log.debug(SecurityUtils.currUser().displayName() + " change the SprintTask [" + this.guid + "] status to [" + status + "]");
        return this;
    }


    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            sprintRepository.saveSprintTask(this);
        } else {
            sprintRepository.updateSprintTask(this);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", guid=" + guid +
                ", id=" + id +
                ", estimateTime=" + estimateTime +
                '}';
    }

    public SprintTask revert() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.PENDING);
        this.status = SprintTaskStatus.CREATED;
        this.pendingTime = null;
        this.executor = null;
        log.debug(SecurityUtils.currUser().displayName() + " revert the SprintTask [" + this.guid + "] from [PENDING] to [CREATED]");
        LogHandler.createSprintActivityLog("revert the task [" + this.name + "],status from [PENDING] to [CREATED]", this);
        return this;
    }

    public SprintTask finish(FinishSprintTaskDto finishSprintTaskDto) {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.PENDING);
        this.status = SprintTaskStatus.FINISHED;
        this.finishTime = DateUtils.now();
        this.finishComment = finishSprintTaskDto.getComment();

        if (finishSprintTaskDto.isLarger4Hours()) {
            this.actualUsedTime = 240;   // 4 hours
            this.largerMaxTime = true;
            log.debug(SecurityUtils.currUser().displayName() + " finish the SprintTask [" + this.guid + "], actual use time {larger than 4 hours}");
        } else {
            String usedTime = finishSprintTaskDto.getUsedTime();
            this.actualUsedTime = SprintTimeUtils.taskTimeAsMinute(usedTime);
            log.debug(SecurityUtils.currUser().displayName() + " finish the SprintTask [" + this.guid + "], actual use time {" + usedTime + "}");
        }
        LogHandler.createSprintActivityLog("finish the task [" + this.name + "]", this);
        return this;
    }

    public SprintTask restore() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.CANCELED);
        this.status = SprintTaskStatus.CREATED;
        this.cancelTime = null;
        this.cancelUser = null;

        log.debug(SecurityUtils.currUser().displayName() + " restore the SprintTask [" + this.guid + "] from [CANCELED] to [CREATED]");
        LogHandler.createSprintActivityLog("restore the task [" + this.name + "]", this);
        return this;
    }

    public SprintTask start() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.CREATED);
        ScrumSecurityChecker.checkSprint(this.sprint(), SprintStatus.PENDING);

        this.status = SprintTaskStatus.PENDING;
        this.pendingTime = DateUtils.now();
        this.executor = SecurityUtils.currUser();

        log.debug(SecurityUtils.currUser().displayName() + " start to do the SprintTask [" + this.guid + "], status is [PENDING] now");
        LogHandler.createSprintActivityLog("start to do the task [" + this.name + "]", this);
        return this;
    }

    /**
     * The task is moved from another sprint or not.
     *
     * @return True is moved,otherwise false
     */
    public boolean moved() {
        int count = sprintRepository.countSprintTaskMoveRecords(this);
        return count > 0;
    }

    public String finishComment() {
        return finishComment;
    }


    public boolean largerMaxTime() {
        return largerMaxTime;
    }

    /**
     * If executor is null, allow
     * If executor equals current user, allow
     * otherwise, popup a confirm message
     *
     * @return StartTaskCheckingResult
     */
    public StartTaskCheckingResult startChecking() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.CREATED);

        StartTaskCheckingResult result = new StartTaskCheckingResult();
        if (this.executor == null) {
            result.setAllow(true);
        } else {
            final User currentUser = SecurityUtils.currUser();
            if (currentUser.equals(this.executor)) {
                result.setAllow(true);
            } else {
                result.setOldExecutor(this.executor.displayName())
                        .setNewExecutor(currentUser.displayName());
            }
        }
        return result;
    }

    public String assignTaskExecutor(String executorGuid) {
        final User oldExecutor = this.executor;
        this.executor = developerRepository.findByGuid(executorGuid);
        sprintRepository.updateSprintTaskExecutor(this.guid, this.executor);

        log.debug(SecurityUtils.currUser().displayName() + " assign SprintTask [" + this.guid + "] executor to [" + this.executor.displayName() + "]"
                + (oldExecutor != null ? ", the SprintTask old executor is [" + oldExecutor.displayName() + "]" : ""));
        LogHandler.createSprintActivityLog("assign the task [" + this.name + "] to [" + this.executor().displayName() + "]", this);
        return sprint.guid();
    }

    public String archiveMe() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.CREATED, SprintTaskStatus.CANCELED);
        this.archived(true).saveOrUpdate();
        LogHandler.createSprintActivityLog("archive the task [" + name + "]", this);
        return this.sprint().guid();
    }

    public String cancelIt() {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.PENDING);
        this.updateStatus(SprintTaskStatus.CANCELED).saveOrUpdate();
        LogHandler.createSprintActivityLog("cancel the task [" + name + "]", this);
        return this.sprint().guid();
    }

    public SprintTaskCommentDto addComment(String content) {
        ScrumSecurityChecker.checkSprintTask(this, SprintTaskStatus.FINISHED);
        SprintTaskComment comment = new SprintTaskComment(this, content);
        comment.saveOrUpdate();
        LogHandler.createSprintActivityLog("add comment [" + content + "] for the task [" + name + "]", this);
        return new SprintTaskCommentDto(comment);
    }

    public SprintTask status(SprintTaskStatus status) {
        this.status = status;
        return this;
    }
}
