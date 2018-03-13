package com.ben.web.servlet;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private String viewName;
	private Map<String,Object> modelMap;
	public Map<String, Object> getModelMap() {
		return modelMap;
	}

	public void setModelMap(Map<String, Object> modelMap) {
		this.modelMap = modelMap;
	}

	public ModelAndView(String viewName, String modelName, Object data) {
		// TODO Auto-generated constructor stub
		this.viewName = viewName;
		this.modelMap = new HashMap<>();
		modelMap.put(modelName, data);
	}
	
	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}



	public ModelAndView() {
	}
	
}
