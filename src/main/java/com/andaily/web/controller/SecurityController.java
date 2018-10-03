package com.andaily.web.controller;

import com.andaily.web.util.CookieUserAssistant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Date: 13-11-21
 *
 * @author Shengzhao Li
 */
@Controller
public class SecurityController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        CookieUserAssistant cookieUserAssistant = new CookieUserAssistant(request).retrieve();
        model.addAttribute("username", cookieUserAssistant.getUsername());
        model.addAttribute("password", cookieUserAssistant.getPassword());
        return "security/login";
    }

}
