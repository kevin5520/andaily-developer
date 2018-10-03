package com.andaily.web.controller.developer.team;

import com.andaily.domain.dto.team.TeamOverviewDto;
import com.andaily.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-12-17
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/team_")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @RequestMapping("overview")
    public String overview(TeamOverviewDto teamOverviewDto, Model model) {
        teamOverviewDto = teamService.loadTeamOverviewDto(teamOverviewDto);
        model.addAttribute("teamOverviewDto", teamOverviewDto);
        return "team/team_overview";
    }


    @RequestMapping("archive/{guid}")
    public String archive(@PathVariable("guid") String guid, Model model) {
        teamService.archiveTeam(guid);

        model.addAttribute("alert", "archiveTeamSuccess");
        return "redirect:../overview";
    }
}
