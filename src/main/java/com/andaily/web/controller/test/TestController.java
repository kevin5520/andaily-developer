package com.andaily.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.user.TestTable;
import com.andaily.domain.user.UserRepository;
import com.andaily.web.context.BeanProvider;

@Controller
@RequestMapping("/testController")
public class TestController {
	private UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

	@RequestMapping("001")
    public String setupForm(Model model) {
		
		System.out.println("abc yes, it is in 001.");
		
        return "test";
    }
	
	@RequestMapping("002")
    public String setupForm2(String userName,String userAge,String userGender,Model model) {
		
		System.out.println("abc yes, it is in 002.");
		
		System.out.println(userName);
		System.out.println(userAge);
		System.out.println(userGender);
		
        model.addAttribute("userName", userName);
        model.addAttribute("userAge", userAge);
        model.addAttribute("userGender", userGender);
        userRepository.saveRecord(userName, userAge, userGender);
        return "test";
    }
	
	@RequestMapping("003")
    public String setupForm3(String userName3, Model model) {
		
		System.out.println("abc yes, it is in 003.");
		
		System.out.println(userName3);
		
		TestTable result = userRepository.findRecord(userName3);
		
		model.addAttribute("userName3", result.getName());
        model.addAttribute("userAge3", result.getAge());
        model.addAttribute("userGender3", result.getGender());
		
        return "test";
    }
	
	@ResponseBody
	@RequestMapping("test_ajax_sumit")
    public String testAjaxSubmit(String userName2, String userAge2, String userGender2, Model model) {
		
		System.out.println("Get into test_ajax_sumit");
		
		System.out.println("userName2 : " + userName2);
		System.out.println("userAge2 : " + userAge2);
		System.out.println("userGender2 : " + userGender2);
		
        return "success";
    }
}
