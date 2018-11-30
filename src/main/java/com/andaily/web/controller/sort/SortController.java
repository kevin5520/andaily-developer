package com.andaily.web.controller.sort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.user.TestTable;
import com.andaily.domain.user.TestsortTable;
import com.andaily.domain.user.UserRepository;
import com.andaily.web.context.BeanProvider;




@Controller
@RequestMapping("/sortController")
public class SortController {
	private UserRepository userRepository = BeanProvider.getBean(UserRepository.class);
	
// (WANG Hanlin)action function for jump the page to output_page(testsort)
	 @RequestMapping("001")
	    public String setupForm( Model model) {
		 System.out.println("sort in 001");
	        return "testsort";
	    }
	 
//(WANG Hanlin)use the input from  output_page(testsort) call the findsortRecord in UserMapper, feed back the data back to output_page(testsort)
	 @RequestMapping("003")
	    public String setupForm3( String userName3, Model model) {
		 
		 System.out.println("sort in 003");
		 
		 System.out.println(userName3);
		 
		 TestsortTable result = userRepository.findsortRecord(userName3);
	        
		 
		   model.addAttribute("userName3",result.getNick_name());
	      /*  model.addAttribute("userAge3",result.getId());*/
		   model.addAttribute("userCount3",result.getCount());
	        
	        return "testsort";
	    }

	 
	

	   
}
