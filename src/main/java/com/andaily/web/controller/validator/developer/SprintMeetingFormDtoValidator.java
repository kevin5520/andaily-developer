package com.andaily.web.controller.validator.developer;

import com.andaily.domain.dto.developer.SprintMeetingFormDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Date: 13-10-1
 *
 * @author Shengzhao Li
 */
@Component()
public class SprintMeetingFormDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SprintMeetingFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "sprint.meeting.form.validation.content.required", "Content is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "sprint.meeting.form.validation.type.required", "Type is required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "meetingDate", "sprint.meeting.form.validation.date.required", "Date is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sprintDto.guid", "sprint.meeting.form.validation.sprint.required", "Sprint is required");
    }
}
