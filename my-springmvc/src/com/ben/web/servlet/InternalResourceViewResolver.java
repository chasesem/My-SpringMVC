package com.ben.web.servlet;


import com.ben.web.tool.ApplicationContext;

public class InternalResourceViewResolver implements ViewResolver{
	private String viewName;


	public InternalResourceViewResolver() {
		// TODO Auto-generated constructor stub
	}


	public View resolveViewName(String viewName) {
		viewName = ApplicationContext.getBean("prefix") +viewName;
		viewName = viewName + ApplicationContext.getBean("suffix");
		View view = new ViewRender(viewName);
		return view;
	}


	public String getViewName() {
		return viewName;
	}


	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
}
