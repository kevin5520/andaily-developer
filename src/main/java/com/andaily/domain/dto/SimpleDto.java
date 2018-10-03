package com.andaily.domain.dto;

import com.andaily.domain.developer.Sprint;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-8-15
 *
 * @author Shengzhao Li
 */
public class SimpleDto extends AbstractDTO {

    protected String name;
    protected boolean selected;

    public SimpleDto() {
    }

    public SimpleDto(String guid, String name) {
        super(guid);
        this.name = name;
    }

    public SimpleDto(String guid, String name, boolean selected) {
        this(guid, name);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<SimpleDto> toSprintSimpleDtos(List<Sprint> sprints) {
        List<SimpleDto> simpleDtos = new ArrayList<SimpleDto>(sprints.size());
        for (Sprint sprint : sprints) {
            simpleDtos.add(new SimpleDto(sprint.guid(), sprint.name()));
        }
        return simpleDtos;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}
