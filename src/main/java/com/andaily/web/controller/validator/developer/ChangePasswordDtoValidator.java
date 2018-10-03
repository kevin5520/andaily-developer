package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.user.ChangePasswordDto;
import com.andaily.domain.shared.PasswordHandler;
import com.andaily.domain.shared.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class ChangePasswordDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordDto changePasswordDto = (ChangePasswordDto) target;

        validateOldPassword(changePasswordDto, errors);
        validateNewPassword(changePasswordDto, errors);
    }

    private void validateNewPassword(ChangePasswordDto changePasswordDto, Errors errors) {
        final String newPassword = changePasswordDto.getNewPassword();
        if (StringUtils.isEmpty(newPassword)) {
            errors.rejectValue("newPassword", "change.password.validation.new.password.required", "New password is required");
        } else {
            if (newPassword.length() < 7) {
                errors.rejectValue("newPassword", "change.password.validation.new.password.length", "Password length must >= 7");
            } else {
                if (!newPassword.equals(changePasswordDto.getRePassword())) {
                    errors.rejectValue("rePassword", "change.password.validation.new.password.incorrect", "Re new password is incorrect");
                }
            }
        }
    }

    private void validateOldPassword(ChangePasswordDto changePasswordDto, Errors errors) {
        final String oldPassword = changePasswordDto.getOldPassword();
        if (StringUtils.isEmpty(oldPassword)) {
            errors.rejectValue("oldPassword", "change.password.validation.old.password.required", "Old password is required");
        } else {
            final String password = SecurityUtils.currUser().password();
            final String encryptPass = PasswordHandler.encryptPassword(oldPassword);
            if (!password.equals(encryptPass)) {
                errors.rejectValue("oldPassword", "change.password.validation.old.password.incorrect", "Old password is incorrect");
            }
        }
    }
}