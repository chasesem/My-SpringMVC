package com.ben.web.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ViewRender implements View {
	
	private String url;
	public ViewRender(String url){
		this.url = url;
	}
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) {
		
		Set<String> set = model.keySet();
		System.out.println(url);
		for(String key :set){
			System.out.println(model.get(key));
			request.setAttribute(key, model.get(key));
		}
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}
