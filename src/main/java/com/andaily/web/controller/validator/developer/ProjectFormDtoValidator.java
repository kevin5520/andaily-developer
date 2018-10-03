package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.domain.shared.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Date: 13-10-22
 *
 * @author Shengzhao Li
 */
@Component
public class ProjectFormDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "project.form.validation.project.name.required", "Project name is required");

        final ProjectFormDto projectFormDto = (ProjectFormDto) target;
        validateFinishDate(projectFormDto, errors);
        validateProductOwners(projectFormDto, errors);
    }

    private void validateProductOwners(ProjectFormDto projectFormDto, Errors errors) {
        final List<String> productOwnerGuids = projectFormDto.getProductOwnerGuids();
        if (productOwnerGuids == null || productOwnerGuids.isEmpty()) {
            errors.rejectValue("productOwnerGuids", "project.form.validation.product.owner.required", "Please choose product owner(s)");
        }
    }

    private void validateFinishDate(ProjectFormDto target, Errors errors) {
        String finishDate = target.getFinishDate();
        if (StringUtils.isNotEmpty(finishDate)) {
            if (!DateUtils.isDate(finishDate)) {
                errors.rejectValue("finishDate", "project.form.validation.illegal.finish.date", "Illegal type finish date");
            }
        }
    }
}
