package com.andaily.web.controller.user;

import com.andaily.domain.dto.team.MyTeamDto;
import com.andaily.domain.dto.user.ChangePasswordDto;
import com.andaily.domain.dto.user.MyProfileDto;
import com.andaily.service.TeamService;
import com.andaily.service.UserService;
import com.andaily.web.controller.validator.developer.ChangePasswordDtoValidator;
import com.andaily.web.controller.validator.developer.MyProfileDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 13-11-24
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/my_profile")
public class MyProfileController {


    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private MyProfileDtoValidator validator;
    @Autowired
    private ChangePasswordDtoValidator changePasswordDtoValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String myProfile(Model model) {
        MyProfileDto profileDto = userService.loadMyProfileDto();
        model.addAttribute("profileDto", profileDto);
        return "user/my_profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitProfile(@ModelAttribute("profileDto") MyProfileDto profileDto, BindingResult result, Model model) {
        validator.validate(profileDto, result);
        if (result.hasErrors()) {
            return "user/my_profile";
        }
        userService.updateMyProfileDto(profileDto);
        model.addAttribute("alert", "updateProfileSuccess");
        return "redirect:my_profile";
    }


    @RequestMapping(value = "change_password", method = RequestMethod.GET)
    public String resetPassword(Model model) {
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "user/change_password";
    }

    @RequestMapping(value = "change_password", method = RequestMethod.POST)
    public String submitResetPassword(@ModelAttribute("changePasswordDto") ChangePasswordDto changePasswordDto, BindingResult result) {
        changePasswordDtoValidator.validate(changePasswordDto, result);
        if (result.hasErrors()) {
            return "user/change_password";
        }
        userService.changeCurrentUserPassword(changePasswordDto);
        return "redirect:../../signout";
    }


    //show my team information
    @RequestMapping("team")
    public String team(Model model) {
        MyTeamDto myTeamDto = teamService.loadMyTeamDto();
        model.addAttribute("myTeamDto", myTeamDto);
        return "team/my_team";
    }


}
