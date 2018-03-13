package com.ben.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ben.web.tool.ApplicationContext;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	/**
	 * Default constructor.
	 */
	public DispatcherServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDispatch(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDispatch(request, response);
	}

	private void doDispatch(HttpServletRequest request,
			HttpServletResponse response) {
		//执行业务逻辑
		HandlerExecutionChain chain = handlerMapping.getHandler(request);
		Controller ctl =  chain.getHandler();
		ModelAndView mv =  handlerAdapter.handle(request, response, ctl);
		ViewResolver vr = new InternalResourceViewResolver();
		View view = vr.resolveViewName(mv.getViewName());
		//将mv传给ViewResolver，调用resolveView，返回view
		view.render(mv.getModelMap(), request, response);
		//调用view render
		
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//解析xml文件，将bean初始化（实例化，赋值）
		context = new ApplicationContext();
		context.build("/spring-mvc.xml");
		for(String key:context.getKeys()) {
			if(key.endsWith("HandlerMapping")) {
				handlerMapping = (HandlerMapping)context.getBean(key);
			}
			if(key.endsWith("HandlerAdapter")){
				handlerAdapter = (HandlerAdapter)context.getBean(key);
			}
			if(key.endsWith("ViewResolver")){
				viewResolver = (ViewResolver)context.getBean(key);
			}
		}
	}
}
