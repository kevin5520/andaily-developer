package com.andaily.domain.dto;

/**
 * Date: 13-9-10
 *
 * @author Shengzhao Li
 */
public class SimpleDisabledDto extends SimpleDto {

    private boolean disabled = false;

    public SimpleDisabledDto() {
    }

    public SimpleDisabledDto(String guid, String name, boolean disabled) {
        super(guid, name);
        this.disabled = disabled;
    }

    public SimpleDisabledDto(String guid, String name, boolean selected, boolean disabled) {
        super(guid, name, selected);
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
