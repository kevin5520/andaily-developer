package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.DeveloperOverviewDto;
import com.andaily.domain.dto.developer.SprintTimeReportDto;
import com.andaily.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-8-6
 *
 * @author Shengzhao Li
 */
@Controller
public class DeveloperController {

    @Autowired
    private ScrumService scrumService;

    //home action
    @RequestMapping(value = {"/", "/developer"})
    public String home(DeveloperOverviewDto developerOverviewDto, Model model) {
        developerOverviewDto = scrumService.loadDeveloperOverviewDto(developerOverviewDto);
        model.addAttribute("developerOverviewDto", developerOverviewDto);
        return "developer_home";
    }

    //Sprint time report
    @RequestMapping(value = {"/time_report/{guid}"})
    public String timeReport(@PathVariable("guid") String guid, SprintTimeReportDto timeReportDto, Model model) {
        timeReportDto.setGuid(guid);
        timeReportDto = scrumService.loadSprintTimeReportDto(timeReportDto);
        model.addAttribute("timeReportDto", timeReportDto);
        return "sprint_time_report";
    }


}
