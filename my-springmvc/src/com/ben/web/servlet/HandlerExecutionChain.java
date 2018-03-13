package com.ben.web.servlet;

public class HandlerExecutionChain {
	private Controller ctl;

	public Controller getHandler() {

		return ctl;
	}

	public void setHandler(Controller ctl) {
		// TODO Auto-generated method stub
		this.ctl = ctl;
	}
}
