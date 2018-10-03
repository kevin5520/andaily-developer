package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.SprintFormDto;
import com.andaily.service.ScrumService;
import com.andaily.web.controller.validator.developer.SprintFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Date: 13-8-14
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/sprint_form/{guid}")
public class SprintFormController {

    @Autowired
    private ScrumService scrumService;
    @Autowired
    private SprintFormDtoValidator validator;


    //pGuid is projectGuid
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, String pGuid, Model model) {
        SprintFormDto sprintFormDto = scrumService.loadSprintFormDto(guid, pGuid);
        model.addAttribute("sprintFormDto", sprintFormDto);
        return "sprint_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("sprintFormDto") SprintFormDto sprintFormDto, BindingResult result) {
        validator.validate(sprintFormDto, result);
        if (result.hasErrors()) {
            return "sprint_form";
        }
        String guid = scrumService.persistSprintFormDto(sprintFormDto);
        final String projectGuid = sprintFormDto.getProjectGuid();
        return "redirect:" + (sprintFormDto.isAddTask() ?
                "../task_form/create?alert=sprintSuccessAddTask&sprintGuid=" + guid :
                "../sprint_overview?alert=sprintFormSuccess&projectGuid=" + projectGuid);
    }


}
