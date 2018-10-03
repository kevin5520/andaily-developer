package com.andaily.web.controller.developer.backlog;

import com.andaily.domain.dto.developer.BacklogOverviewDto;
import com.andaily.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-9-8
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/backlog_overview")
public class BacklogOverviewController {

    @Autowired
    private BacklogService backlogService;


    @RequestMapping
    public String overview(BacklogOverviewDto backlogOverviewDto, Model model) {
        backlogOverviewDto = backlogService.loadBacklogOverviewDto(backlogOverviewDto);
        model.addAttribute("backlogOverviewDto", backlogOverviewDto);
        return "backlog_overview";
    }


}
