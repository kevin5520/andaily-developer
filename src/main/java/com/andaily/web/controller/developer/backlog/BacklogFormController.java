package com.andaily.web.controller.developer.backlog;

import com.andaily.domain.dto.developer.BacklogFormDto;
import com.andaily.service.BacklogService;
import com.andaily.web.controller.validator.developer.BacklogFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 13-9-7
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/backlog_form/{guid}")
public class BacklogFormController {


    @Autowired
    private BacklogService backlogService;
    @Autowired
    private BacklogFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, String pGuid, Model model) {
        BacklogFormDto backlogFormDto = backlogService.loadBacklogFormDto(guid, pGuid);
        model.addAttribute("backlogFormDto", backlogFormDto);
        return "backlog_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("backlogFormDto") BacklogFormDto backlogFormDto, BindingResult result) {
        validator.validate(backlogFormDto, result);
        if (result.hasErrors()) {
            return "backlog_form";
        }
        backlogService.persistBacklogFormDto(backlogFormDto);

        String url = backlogFormDto.isAddNext() ? "../backlog_form/create?alert=successAddNext&pGuid=" + backlogFormDto.getProjectGuid()
                : "../backlog_overview?alert=successAddBacklog&projectGuid=" + backlogFormDto.getProjectGuid();
        return "redirect:" + url;
    }
}
