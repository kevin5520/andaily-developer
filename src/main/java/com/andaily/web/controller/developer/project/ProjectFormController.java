package com.andaily.web.controller.developer.project;

import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.service.ProjectService;
import com.andaily.web.controller.validator.developer.ProjectFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 13-10-21
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/project_form/{guid}")
public class ProjectFormController {


    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFormDtoValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, Model model) {
        ProjectFormDto projectFormDto = projectService.loadProjectFormDto(guid);
        model.addAttribute("projectFormDto", projectFormDto);
        return "project/project_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("projectFormDto") ProjectFormDto projectFormDto, BindingResult result) {
        validator.validate(projectFormDto, result);
        if (result.hasErrors()) {
            return "project/project_form";
        }
        projectService.persistProjectFormDto(projectFormDto);
        return "redirect:../project_/overview?alert=projectFormSuccess";
    }
}
