package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.dto.AbstractDTO;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-8-7
 *
 * @author Shengzhao Li
 */
public class SprintSimpleDto extends AbstractDTO {

    private String name;
    private SprintStatus status;
    private boolean defaultSprint;

    public SprintSimpleDto() {
    }

    public SprintSimpleDto(Sprint sprint) {
        super(sprint.guid());
        this.name = generateName(sprint);
        this.status = sprint.status();
        this.defaultSprint = sprint.defaultSprint();
    }


    //Name format:  sprint name + [ + project code/project name +]
    private String generateName(Sprint sprint) {
        final Project project = sprint.project();
        if (project == null) {
            return sprint.name();
        }
        if (StringUtils.isEmpty(project.code())) {
            return sprint.name() + " [" + project.name() + "]";
        }
        return sprint.name() + " [" + project.code() + "]";
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultSprint() {
        return defaultSprint;
    }

    public void setDefaultSprint(boolean defaultSprint) {
        this.defaultSprint = defaultSprint;
    }

    public static List<SprintSimpleDto> toDtos(List<Sprint> sprints) {
        List<SprintSimpleDto> dtos = new ArrayList<>(sprints.size());
        for (Sprint sprint : sprints) {
            dtos.add(new SprintSimpleDto(sprint));
        }
        return dtos;
    }
}
