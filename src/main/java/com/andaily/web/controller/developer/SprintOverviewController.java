package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.SprintOverviewDto;
import com.andaily.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-8-30
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/sprint_overview")
public class SprintOverviewController {

    @Autowired
    private ScrumService scrumService;


    @RequestMapping
    public String overview(SprintOverviewDto sprintOverviewDto, Model model) {
        sprintOverviewDto = scrumService.loadSprintOverviewDto(sprintOverviewDto);
        model.addAttribute("sprintOverviewDto", sprintOverviewDto);
        return "sprint_overview";
    }


}
