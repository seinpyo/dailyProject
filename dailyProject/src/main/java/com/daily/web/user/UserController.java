package com.daily.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpServletRequest req) {
		return "login";
	}
	
	

}
