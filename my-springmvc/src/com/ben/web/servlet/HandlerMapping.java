package com.ben.web.servlet;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
	HandlerExecutionChain  getHandler(HttpServletRequest request);
}
