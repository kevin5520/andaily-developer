package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.user.MyProfileDto;
import com.andaily.domain.shared.MatchUtils;
import com.andaily.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class MyProfileDtoValidator implements Validator {


    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MyProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MyProfileDto profileDto = (MyProfileDto) target;
        validateEmail(profileDto, errors);
    }


    private void validateEmail(MyProfileDto profileDto, Errors errors) {
        final String email = profileDto.getEmail();
        if (StringUtils.isEmpty(email)) {
            errors.rejectValue("email", "my.profile.validation.email.required", "Email is required");
            return;
        }

        if (!MatchUtils.isEmail(email)) {
            errors.rejectValue("email", "my.profile.validation.email.incorrect", "Email is incorrect format");
            return;
        }
        if (email.equals(profileDto.getExistEmail())) {
            return;
        }

        boolean unique = userService.uniqueEmail(email);
        if (!unique) {
            errors.rejectValue("email", "my.profile.validation.email.exist", "The email already existed");
        }
    }

}