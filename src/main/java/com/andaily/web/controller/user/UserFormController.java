package com.andaily.web.controller.user;

import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.service.UserService;
import com.andaily.web.controller.validator.developer.UserFormDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 13-11-26
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/user_/user_form/{guid}")
public class UserFormController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserFormDTOValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@PathVariable("guid") String guid, String tGuid, Model model) {
        UserFormDto userFormDto = userService.loadUserFormDto(guid, tGuid);
        model.addAttribute("userFormDto", userFormDto);
        return "user/user_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute("userFormDto") UserFormDto userFormDto, BindingResult result) {
        validator.validate(userFormDto, result);
        if (result.hasErrors()) {
            return "user/user_form";
        }
        userService.persistUserForm(userFormDto);

        final String teamGuid = userFormDto.getTeamGuid();
        return "redirect:" + (userFormDto.isAddNext() ?
                "../user_form/create?tGuid=" + teamGuid + "&alert=saveUserSuccess"
                : "../overview?alert=saveUserSuccess&teamGuid=" + teamGuid);
    }

}
