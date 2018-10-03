package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.SprintMeetingFormDto;
import com.andaily.service.ScrumService;
import com.andaily.web.controller.validator.developer.SprintMeetingFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 13-10-1
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/sprint/meeting_form/{guid}")
public class SprintMeetingFormController {

    @Autowired
    private ScrumService scrumService;
    @Autowired
    private SprintMeetingFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, @RequestParam String sprintGuid, Model model) {
        SprintMeetingFormDto sprintMeetingFormDto = scrumService.loadSprintMeetingFormDto(guid, sprintGuid);
        model.addAttribute("sprintMeetingFormDto", sprintMeetingFormDto);
        return "sprint_meeting_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("sprintMeetingFormDto") SprintMeetingFormDto sprintMeetingFormDto, BindingResult result) {
        validator.validate(sprintMeetingFormDto, result);
        if (result.hasErrors()) {
            return "sprint_meeting_form";
        }
        scrumService.persistSprintMeetingFormDto(sprintMeetingFormDto);
        //redirect
        String sprintGuid = sprintMeetingFormDto.getSprintDto().getGuid();
        String backUrl = (sprintMeetingFormDto.isBackToOverview() ? "../../sprint_meeting/overview?sprintGuid=" + sprintGuid
                : "../..?currentSprint.guid=" + sprintGuid);
        return "redirect:" + backUrl + "&alert=addMeetingSuccess";
    }

}
