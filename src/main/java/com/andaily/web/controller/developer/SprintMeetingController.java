package com.andaily.web.controller.developer;

import com.andaily.domain.dto.developer.SprintMeetingDto;
import com.andaily.domain.dto.developer.SprintMeetingOverviewDto;
import com.andaily.service.ScrumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-10-2
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/sprint_meeting")
public class SprintMeetingController {


    @Autowired
    private ScrumService scrumService;

    @RequestMapping("overview")
    public String overview(SprintMeetingOverviewDto overviewDto, Model model) {
        overviewDto = scrumService.loadSprintMeetingOverviewDto(overviewDto);
        model.addAttribute("overviewDto", overviewDto);
        return "sprint_meeting_overview";
    }

    @RequestMapping("details/{guid}")
    public String details(@PathVariable("guid") String guid, Model model) {
        SprintMeetingDto sprintMeetingDto = scrumService.loadSprintMeetingDto(guid);
        model.addAttribute("sprintMeetingDto", sprintMeetingDto);
        return "sprint_meeting_details";
    }


}
