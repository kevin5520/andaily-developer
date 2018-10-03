package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintTaskCommentsDto extends AbstractDTO {


    private List<SprintTaskCommentDto> comments = new ArrayList<>();


    public SprintTaskCommentsDto() {
    }

    public SprintTaskCommentsDto(String guid) {
        super(guid);
    }

    public List<SprintTaskCommentDto> getComments() {
        return comments;
    }

    public SprintTaskCommentsDto setComments(List<SprintTaskCommentDto> comments) {
        this.comments = comments;
        return this;
    }
}