package com.andaily.web.controller.developer.team;

import com.andaily.domain.dto.team.TeamFormDto;
import com.andaily.service.TeamService;
import com.andaily.web.controller.validator.developer.TeamFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 14-1-6
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/team_/team_form/{guid}")
public class TeamFormController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamFormDtoValidator validator;


    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, Model model) {
        TeamFormDto teamFormDto = teamService.loadTeamFormDto(guid);
        model.addAttribute("teamFormDto", teamFormDto);
        return "team/team_form";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("teamFormDto") TeamFormDto teamFormDto, BindingResult result) {
        validator.validate(teamFormDto, result);
        if (result.hasErrors()) {
            return "team/team_form";
        }
        teamService.persistTeamFormDto(teamFormDto);
        return "redirect:../overview?alert=saveTeamSuccess";
    }

}
