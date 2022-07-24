package com.web.dailyProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	@RequestMapping("/") 
	public @ResponseBody String root() throws Exception {
		return "Security (1)";
	}
	
	@RequestMapping("/guest/welcome")
	public String welcomeGuest() {
		return "/guest/welcomeGuest";
	}
	
	@RequestMapping("/member/welcome")
	public String welcomeMember() {
		return "/member/welcomeMember";
	}
	
	@RequestMapping("/admin/welcome")
	public String welcomeAdmin() {
		return "/admin/welcomeAdmin";
	}
}
