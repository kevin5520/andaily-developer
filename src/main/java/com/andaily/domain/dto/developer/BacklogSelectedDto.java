package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;

/**
 * @author Shengzhao Li
 */
public class BacklogSelectedDto extends BacklogDto {

    private boolean selected;

    public BacklogSelectedDto() {
    }

    public BacklogSelectedDto(Backlog backlog) {
        super(backlog);
    }

    public boolean isSelected() {
        return selected;
    }

    public BacklogSelectedDto setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }
}