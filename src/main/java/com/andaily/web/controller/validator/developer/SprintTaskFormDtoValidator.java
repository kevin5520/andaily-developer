package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.developer.SprintTaskFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Date: 13-8-18
 *
 * @author Shengzhao Li
 */
@Component
public class SprintTaskFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SprintTaskFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SprintTaskFormDto taskFormDto = (SprintTaskFormDto) target;
        String name = taskFormDto.getName();
        if (StringUtils.isEmpty(name)) {
            errors.rejectValue("name", "sprint.task.form.validation.name.required", "Task name is required");
        }
    }


}
