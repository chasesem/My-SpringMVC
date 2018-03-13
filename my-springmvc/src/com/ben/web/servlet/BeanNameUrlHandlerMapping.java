package com.ben.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.ben.web.tool.ApplicationContext;

public class BeanNameUrlHandlerMapping implements HandlerMapping {

	public HandlerExecutionChain getHandler(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String url = request.getServletPath();
		System.out.println(url+"    123");
		Controller ctl = (Controller) ApplicationContext.getBean(url);
		HandlerExecutionChain chain = new HandlerExecutionChain();
		chain.setHandler(ctl);
		return chain;
	}

}
