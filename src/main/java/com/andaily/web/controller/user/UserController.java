package com.andaily.web.controller.user;

import com.andaily.domain.dto.user.UserOverviewDto;
import com.andaily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Date: 13-11-24
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/user_")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("overview")
    public String overview(UserOverviewDto userOverviewDto, Model model) {
        userOverviewDto = userService.loadUserOverviewDto(userOverviewDto);
        model.addAttribute("userOverviewDto", userOverviewDto);
        return "user/user_overview";
    }


    @RequestMapping("archive/{guid}/{archive}")
    public String archive(@PathVariable("guid") String guid, @PathVariable("archive") boolean archive) {
        String teamGuid = userService.archiveUser(guid, archive);

        String alert = archive ? "archiveSuccess" : "activeSuccess";
        return "redirect:../../overview?alert=" + alert + (teamGuid != null ? "&teamGuid=" + teamGuid : "");
    }

    @RequestMapping("reset_password/{guid}")
    public String resetPassword(@PathVariable("guid") String guid, Model model) {
        String newPass = userService.resetPassword(guid);
        model.addAttribute("newPass", newPass);
        return "user/reset_password";
    }

}
