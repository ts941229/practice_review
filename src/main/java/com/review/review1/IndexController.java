package com.review.review1;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String mainPage() {
		return "/main/index";
	}
	
}
