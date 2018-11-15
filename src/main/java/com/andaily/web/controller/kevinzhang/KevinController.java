package com.andaily.web.controller.kevinzhang;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andaily.domain.user.TestTable;
import com.andaily.domain.user.UserRepository;
import com.andaily.web.context.BeanProvider;

@Controller
@RequestMapping("/KevinController")
public class KevinController {
	
	private UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
	
	@RequestMapping("load_my_first_page")
    public String loadSprintProjects(Model model) {
		
		System.out.println("Get into load_my_first_page");
		
        return "kevin/kevin_test_page";
    }
	
	@RequestMapping("test_form_sumit")
    public String testFormSubmit(String userName, String userAge, String userGender, Model model) {
		
		System.out.println("Get into test_form_sumit");
		
		System.out.println("userName : " + userName);
		System.out.println("userAge : " + userAge);
		System.out.println("userGender : " + userGender);
		
		model.addAttribute("userName", userName);
		model.addAttribute("userAge", userAge);
		model.addAttribute("userGender", userGender);
		
		//userRepository.saveRecord(userName, userAge, userGender);
		
        return "kevin/kevin_test_page";
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
	
	@RequestMapping("test_find_record")
    public String testFindRecord(String userName3, Model model) {
		
		System.out.println("Get into test_find_record");
		
		System.out.println("userName : " + userName3);
		
		TestTable record = userRepository.findRecord(userName3);
		model.addAttribute("userName3", record.getName());
		model.addAttribute("userAge3", record.getAge());
		model.addAttribute("userGender3", record.getGender());
		
        return "kevin/kevin_test_page";
    }

}
