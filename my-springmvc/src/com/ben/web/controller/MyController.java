package com.ben.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ben.web.service.UserService;
import com.ben.web.service.impl.UserServiceImpl;
import com.ben.web.servlet.Controller;
import com.ben.web.servlet.ModelAndView;

public class MyController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("my controller");
		UserService service = new UserServiceImpl();
		Map<String, String> map = service.getUser();
		
		return new ModelAndView("userlist","user",map);
	}

}
