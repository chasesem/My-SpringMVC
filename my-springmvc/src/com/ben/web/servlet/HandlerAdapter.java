package com.ben.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	
	ModelAndView handle(HttpServletRequest request,HttpServletResponse response,Controller handler);
}
