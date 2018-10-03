package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.dto.SimpleDisabledDto;
import com.andaily.domain.dto.developer.commons.BacklogDocumentDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-9-11
 *
 * @author Shengzhao Li
 */
public class SprintFormBacklogDto extends SimpleDisabledDto {

    private int number;
    private int estimateTime;
    private List<BacklogDocumentDto> documentDtos = new ArrayList<>();

    //Already references tasks
    private int referencedTasks;

    public SprintFormBacklogDto() {
    }

    public SprintFormBacklogDto(Backlog backlog) {
        super(backlog.guid(), backlog.content(), false);
        this.number = backlog.number();
        this.estimateTime = backlog.estimateTime();

        this.referencedTasks = backlog.referencedSprintTasks();
        this.documentDtos = BacklogDocumentDto.toBacklogDtos(backlog.documents());
    }

    public SprintFormBacklogDto(String guid, String name, boolean disabled) {
        super(guid, name, disabled);
    }

    public SprintFormBacklogDto(String guid, String name, boolean selected, boolean disabled) {
        super(guid, name, selected, disabled);
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isShowReferencedTasks() {
        return referencedTasks > 0;
    }

    public int getReferencedTasks() {
        return referencedTasks;
    }

    public void setReferencedTasks(int referencedTasks) {
        this.referencedTasks = referencedTasks;
    }

    public List<BacklogDocumentDto> getDocumentDtos() {
        return documentDtos;
    }

    public void setDocumentDtos(List<BacklogDocumentDto> documentDtos) {
        this.documentDtos = documentDtos;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public SprintFormBacklogDto setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
        return this;
    }
}
