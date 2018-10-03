package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.user.SystemConfigurationFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class SystemConfigurationFormDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return SystemConfigurationFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "perPageSize", "system.config.validation.perpagesize.required", "Per page data size is required");
    }
}