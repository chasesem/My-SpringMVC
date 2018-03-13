package com.ben.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter{

	public ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Controller handler) {
		// TODO Auto-generated method stub
		return handler.handleRequest(request, response);
		
	}
	
}
