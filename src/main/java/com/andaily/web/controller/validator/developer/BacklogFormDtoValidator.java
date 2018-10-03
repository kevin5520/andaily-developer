package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.developer.BacklogFormDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Date: 13-9-8
 *
 * @author Shengzhao Li
 */
@Component
public class BacklogFormDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BacklogFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "backlog.form.validation.content.required", "Backlog content is required");
        BacklogFormDto backlogFormDto = (BacklogFormDto) target;

        validateEstimateTime(backlogFormDto, errors);
        validateProject(backlogFormDto, errors);
    }

    private void validateProject(BacklogFormDto backlogFormDto, Errors errors) {
        String projectGuid = backlogFormDto.getProjectGuid();
        if (StringUtils.isEmpty(projectGuid)) {
            errors.rejectValue("projectGuid", "backlog.form.validation.choose.project", "Choose a project please");
        }
    }

    private void validateEstimateTime(BacklogFormDto backlogFormDto, Errors errors) {
        int estimateTime = backlogFormDto.getEstimateTime();
        if (estimateTime <= 0) {
            errors.rejectValue("estimateTime", "backlog.form.validation.estimate.than.zero", "Estimate time should be > 0");
        }
    }
}
