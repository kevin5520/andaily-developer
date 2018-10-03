package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.shared.MatchUtils;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Date: 13-12-03
 *
 * @author Shengzhao Li
 */
@Component
public class UserFormDTOValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto userFormDTO = (UserFormDto) target;
        validateEmail(userFormDTO, errors);

        validatePassword(userFormDTO, errors);
        validateCellphone(userFormDTO, errors);
        validateScrumTerm(userFormDTO, errors);
    }

    private void validateScrumTerm(UserFormDto userFormDTO, Errors errors) {
        final ScrumTerm scrumTerm = userFormDTO.getScrumTerm();
        if (scrumTerm == null) {
            errors.rejectValue("scrumTerm", "user.form.validation.role.required", "User role is required");
        }
    }

    private void validateCellphone(UserFormDto userFormDTO, Errors errors) {
        final String cellPhone = userFormDTO.getCellPhone();

        if (StringUtils.isNotEmpty(cellPhone)
                && !cellPhone.equals(userFormDTO.getExistCellPhone())
                && !userService.uniqueCellphone(cellPhone)) {
            errors.rejectValue("cellPhone", "user.form.validation.cellphone.exist", "Cellphone already existed");
        }
    }

    private void validatePassword(UserFormDto userFormDTO, Errors errors) {
        if (!userFormDTO.isNewly()) {
            //Ignore validation if it is edit
            return;
        }
        final String password = userFormDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            errors.rejectValue("password", "user.form.validation.password.required", "Password is required");
            return;
        }

        if (password.length() < 7) {
            errors.rejectValue("password", "user.form.validation.password.length", "Password length must >= 7");
            return;
        }

        if (!password.equals(userFormDTO.getRePassword())) {
            errors.rejectValue("rePassword", "user.form.validation.password.incorrect", "Re-enter the password is not the same");
        }
    }

    private void validateEmail(UserFormDto userFormDTO, Errors errors) {
        final String email = userFormDTO.getEmail();
        if (StringUtils.isEmpty(email)) {
            errors.rejectValue("email", "user.form.validation.email.required", "Email is required");
            return;
        }

        if (!MatchUtils.isEmail(email)) {
            errors.rejectValue("email", "user.form.validation.email.incorrect", "Email is incorrect format");
            return;
        }
        if (email.equals(userFormDTO.getExistEmail())) {
            return;
        }

        boolean unique = userService.uniqueEmail(email);
        if (!unique) {
            errors.rejectValue("email", "user.form.validation.email.exist", "The email already existed");
        }
    }

}
