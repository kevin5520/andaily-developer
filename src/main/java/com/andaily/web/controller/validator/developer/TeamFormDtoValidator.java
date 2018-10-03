package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.team.TeamFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class TeamFormDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return TeamFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "team.form.validation.name.required", "Team name is required");

        TeamFormDto formDto = (TeamFormDto) target;
        if (formDto.getMasters() == null || formDto.getMasters().isEmpty()) {
            errors.rejectValue("masters", "team.form.validation.scrum.master.required", "Please choose Scrum master");
        }

        if (formDto.getMembers() == null || formDto.getMembers().isEmpty()) {
            errors.rejectValue("members", "team.form.validation.scrum.member.required", "Please choose Scrum members");
        }

    }
}