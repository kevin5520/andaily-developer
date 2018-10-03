package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.SprintTaskFormDto;
import com.andaily.service.ScrumService;
import com.andaily.web.controller.validator.developer.SprintTaskFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 13-8-17
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/task_form/{guid}")
public class SprintTaskFormController {

    @Autowired
    private ScrumService scrumService;
    @Autowired
    private SprintTaskFormDtoValidator validator;


    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, @RequestParam(required = true) String sprintGuid, Model model) {
        SprintTaskFormDto sprintTaskFormDto = scrumService.loadSprintTaskFormDto(guid, sprintGuid);
        model.addAttribute("sprintTaskFormDto", sprintTaskFormDto);
        return "sprint_task_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("sprintTaskFormDto") SprintTaskFormDto sprintTaskFormDto, BindingResult result) {
        validator.validate(sprintTaskFormDto, result);
        if (result.hasErrors()) {
            return "sprint_task_form";
        }
        scrumService.persistSprintTaskFormDto(sprintTaskFormDto);

        String sprintGuid = sprintTaskFormDto.getSprintGuid();
        return "redirect:" + (sprintTaskFormDto.isAddNext() ?
                "create?alert=successAndAddNext&sprintGuid=" + sprintGuid :
                "../../developer?currentSprint.guid=" + sprintGuid
                        + "&alert=taskFormSuccess&status=" + sprintTaskFormDto.getStatus());
    }
}
