package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutProcessHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();			//세션정보 날려버리기~~
		
		
		return "redirect:./main_page.do";
	}

}
