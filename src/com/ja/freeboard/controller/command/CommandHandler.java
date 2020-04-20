package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//./Java Resources/src/com.ja.freeboard.controller.command/CommandHandler(인터페이스)


public interface CommandHandler {
	//모든 핸들러가 인터페이스를 상속받아 process 오버라이딩 하자! 의존도 낮추기 위함(안그러면 서블릿에서 싹 다 짜야됨).
	public String process(HttpServletRequest request, HttpServletResponse response);
	
}
