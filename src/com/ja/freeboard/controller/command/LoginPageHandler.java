package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//./Java Resources/src/com.ja.freeboard.controller.command/LoginPageHandler


public class LoginPageHandler implements CommandHandler{
	//인터페이스 상속받아서 오버라이딩 하자!
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		
		//리다이렉트, 포워딩은 각 핸들러에서 선택! -> 그 담에 UsingURI로 넘겨주기만 한다.
		return "/WEB-INF/view/login_page.jsp";
	}
	
}
