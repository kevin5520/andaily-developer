package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogType;
import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintPriority;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.SimpleDto;
import com.andaily.domain.dto.developer.commons.BacklogDocumentDto;
import com.andaily.domain.dto.developer.project.ProjectDto;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.dto.user.UserDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.st.STRender;

import java.util.*;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
public class BacklogDto extends AbstractDTO {

    protected SprintPriority priority = SprintPriority.DEFAULT;

    protected String content;
    protected int number;

    protected BacklogType type = BacklogType.NEEDS;

    protected SprintDto sprint;

    protected UserDto joinSprintUser;
    protected int estimateTime;

    protected String joinSprintTime;
    protected String createTime;
    protected DeveloperDto creator;

    protected ProjectDto projectDto;
    protected int referencedTasks;
    protected List<BacklogDocumentDto> documentDtos = new ArrayList<>();

    public BacklogDto() {
    }

    public BacklogDto(Backlog backlog) {
        super(backlog.guid());
        this.createTime = DateUtils.toDateTime(backlog.createTime());
        this.priority = backlog.priority();
        this.content = backlog.content();

        this.type = backlog.type();
        this.estimateTime = backlog.estimateTime();
        this.creator = new DeveloperDto((Developer) backlog.creator());
        this.number = backlog.number();

        this.referencedTasks = backlog.referencedSprintTasks();

        Sprint sprint1 = backlog.sprint();
        if (sprint1 != null) {
            this.sprint = new SprintDto(sprint1);
        }
        Date date = backlog.joinSprintTime();
        if (date != null) {
            this.joinSprintTime = DateUtils.toDateTime(date);
        }
        User user = backlog.joinSprintUser();
        if (user != null) {
            this.joinSprintUser = new UserDto(user);
        }

        this.projectDto = new ProjectDto(backlog.project());
        this.documentDtos = BacklogDocumentDto.toBacklogDtos(backlog.documents());
    }

    public int getReferencedTasks() {
        return referencedTasks;
    }

    public void setReferencedTasks(int referencedTasks) {
        this.referencedTasks = referencedTasks;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<BacklogDocumentDto> getDocumentDtos() {
        return documentDtos;
    }

    public int getDocumentsSize() {
        return documentDtos.size();
    }

    public void setDocumentDtos(List<BacklogDocumentDto> documentDtos) {
        this.documentDtos = documentDtos;
    }

    public DeveloperDto getCreator() {
        return creator;
    }

    public void setCreator(DeveloperDto creator) {
        this.creator = creator;
    }

    public boolean isReferSprint() {
        return this.sprint != null;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public String getHtmlTip() {
        if (isNewly()) {
            return "";
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("content", content);
        model.put("type", type.getLabel());
        model.put("estimateTime", estimateTime);
        model.put("creator", creator.getShowName());
        model.put("priority", priority.getLabel());
        model.put("createTime", createTime);
        model.put("number", number);

        final boolean chinese = SecurityUtils.currUser().language().isChinese();
        final String path = chinese ? "template/backlog_tip_cn.html" : "template/backlog_tip.html";
        STRender stRender = new STRender(path, model);
        return stRender.render();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public SprintPriority getPriority() {
        return priority;
    }

    public void setPriority(SprintPriority priority) {
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BacklogType getType() {
        return type;
    }

    public void setType(BacklogType type) {
        this.type = type;
    }

    public SprintDto getSprint() {
        return sprint;
    }

    public void setSprint(SprintDto sprint) {
        this.sprint = sprint;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
    }

    public UserDto getJoinSprintUser() {
        return joinSprintUser;
    }

    public void setJoinSprintUser(UserDto joinSprintUser) {
        this.joinSprintUser = joinSprintUser;
    }

    public String getJoinSprintTime() {
        return joinSprintTime;
    }

    public void setJoinSprintTime(String joinSprintTime) {
        this.joinSprintTime = joinSprintTime;
    }

    public static List<SimpleDto> toSimpleDtos(List<Backlog> backlogs, String selectGuid) {
        List<SimpleDto> simpleDtos = new ArrayList<SimpleDto>(backlogs.size());
        for (Backlog backlog : backlogs) {
            String guid1 = backlog.guid();
            simpleDtos.add(new SimpleDto(guid1, backlog.content(), guid1.equals(selectGuid)));
        }
        return simpleDtos;
    }

    public static List<BacklogDto> toDtos(List<Backlog> backlogs) {
        List<BacklogDto> backlogDtos = new ArrayList<BacklogDto>(backlogs.size());
        for (Backlog backlog : backlogs) {
            backlogDtos.add(new BacklogDto(backlog));
        }
        return backlogDtos;
    }

    public static List<SprintFormBacklogDto> toSprintFormBacklogDtos(List<Backlog> backlogs, String selectGuid) {
        List<SprintFormBacklogDto> simpleDtos = new ArrayList<SprintFormBacklogDto>(backlogs.size());
        for (Backlog backlog : backlogs) {
            SprintFormBacklogDto backlogDto = createSprintFormBacklogDto(selectGuid, backlog);
            simpleDtos.add(backlogDto);
        }
        return simpleDtos;
    }

    private static SprintFormBacklogDto createSprintFormBacklogDto(String selectGuid, Backlog backlog) {
        String guid1 = backlog.guid();
        SprintFormBacklogDto backlogDto = new SprintFormBacklogDto(guid1, backlog.content(), guid1.equals(selectGuid), false);

        backlogDto.setEstimateTime(backlog.estimateTime());
        backlogDto.setReferencedTasks(backlog.referencedSprintTasks());
        backlogDto.setNumber(backlog.number());

        return backlogDto;
    }
}
