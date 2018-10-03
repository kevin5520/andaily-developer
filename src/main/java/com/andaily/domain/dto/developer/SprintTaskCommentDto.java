package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.SprintTaskComment;
import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintTaskCommentDto extends AbstractDTO {

    private String who;
    private String createDate;
    private String content;

    private boolean myCreated;

    public SprintTaskCommentDto() {
    }

    public SprintTaskCommentDto(SprintTaskComment comment) {
        super(comment.guid());
        final Developer who1 = comment.who();
        this.who = who1.displayName();
        this.myCreated = SecurityUtils.currUser().equals(who1);

        this.createDate = DateUtils.toDateText(comment.createTime());
        this.content = comment.content();
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMyCreated() {
        return myCreated;
    }

    public void setMyCreated(boolean myCreated) {
        this.myCreated = myCreated;
    }

    public static List<SprintTaskCommentDto> toDtos(List<SprintTaskComment> comments) {
        List<SprintTaskCommentDto> dtos = new ArrayList<>(comments.size());
        for (SprintTaskComment comment : comments) {
            dtos.add(new SprintTaskCommentDto(comment));
        }
        return dtos;
    }
}