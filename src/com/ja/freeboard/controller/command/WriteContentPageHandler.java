package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteContentPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		//예외처리코드 필요 비로그인일 때 로그인 페이지로 이동시키는 if문 필요함.
		
		return "/WEB-INF/view/write_content_page.jsp";
	}

}
