package com.andaily.web.controller.user;

import com.andaily.domain.dto.user.SystemConfigurationFormDto;
import com.andaily.service.SecurityService;
import com.andaily.web.controller.validator.developer.SystemConfigurationFormDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Date: 15-04-03
 *
 * @author Shengzhao Li
 */
@Controller
@RequestMapping("/system")
public class SystemController {


    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemConfigurationFormDtoValidator validator;


    @RequestMapping(value = "config", method = RequestMethod.GET)
    public String config(Model model) {
        SystemConfigurationFormDto formDto = securityService.loadSystemConfigurationFormDto();
        model.addAttribute("formDto", formDto);
        return "user/system_config";
    }


    @RequestMapping(value = "config", method = RequestMethod.POST)
    public String submitConfig(@ModelAttribute("formDto") SystemConfigurationFormDto formDto, BindingResult result) {
        validator.validate(formDto, result);
        if (result.hasErrors()) {
            return "user/system_config";
        }
        securityService.updateSystemConfiguration(formDto);
        return "redirect:config?alert=saveSuccess";
    }


}
