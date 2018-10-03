package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.developer.SprintFormDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.service.ScrumService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Date: 13-8-15
 *
 * @author Shengzhao Li
 */
@Component
public class SprintFormDtoValidator implements Validator {

    @Autowired
    private ScrumService scrumService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SprintFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SprintFormDto sprintFormDto = (SprintFormDto) target;
        validateName(errors, sprintFormDto);
        validateDates(errors, sprintFormDto);
        validateProject(sprintFormDto, errors);
        validateTeam(sprintFormDto, errors);
    }

    private void validateTeam(SprintFormDto sprintFormDto, Errors errors) {
        final String teamGuid = sprintFormDto.getTeamGuid();
        if (StringUtils.isEmpty(teamGuid)) {
            errors.rejectValue("teamGuid", "sprint.form.validation.choose.team", "Choose a team please");
        }
    }

    private void validateProject(SprintFormDto sprintFormDto, Errors errors) {
        String projectGuid = sprintFormDto.getProjectGuid();
        if (StringUtils.isEmpty(projectGuid)) {
            errors.rejectValue("projectGuid", "sprint.form.validation.choose.project", "Choose a project please");
        }
    }

    private void validateDates(Errors errors, SprintFormDto sprintFormDto) {
        String startDate = sprintFormDto.getStartDate();
        Date start = null;
        if (StringUtils.isEmpty(startDate)) {
            errors.rejectValue("startDate", "sprint.form.validation.start.date.required", "Start date is required");
        } else {
            start = DateUtils.getDate(startDate);
        }
        String deadline = sprintFormDto.getDeadline();
        Date end = null;
        if (StringUtils.isEmpty(deadline)) {
            errors.rejectValue("deadline", "sprint.form.validation.deadline.required", "Deadline is required");
        } else {
            end = DateUtils.getDate(deadline);
            if (sprintFormDto.isNewly() && end.before(DateUtils.now())) {
                errors.rejectValue("deadline", "sprint.form.validation.deadline.after.today", "Deadline must be after today");
            }
        }
        if (start != null && end != null && start.after(end)) {
            errors.rejectValue("endDate", "sprint.form.validation.end.date.after.start", "Deadline must be after start date");
        }
    }

    private void validateName(Errors errors, SprintFormDto sprintFormDto) {
        String name = sprintFormDto.getName();
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "sprint.form.validation.sprint.name.required", "Sprint name is required");
        } else {
            if (!name.equals(sprintFormDto.getExistName())) {
                boolean exist = scrumService.loadExistSprintName(name);
                if (exist) {
                    errors.rejectValue("name", "sprint.form.validation.sprint.name.exists", "Sprint name is existed");
                }
            }
        }
    }
}
