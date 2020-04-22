package com.ja.freeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ja.freeboard.controller.command.CommandHandler;


//./Java Resources/src/com.ja.freeboard.controller/ControllerUsingURI


/**
 * Servlet implementation class ControllerUsingURI
 */
//@WebServlet("/ControllerUsingURI")			//web.xml에서 처리하자!
public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommandFactory commandFactory;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsingURI() {
        super();
        
        commandFactory = new CommandFactory();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//System.out.println("넘어온 명령어 : " + request.getRequestURI());
		
		//어떤 명령어가 들어왔는지 확인
		String command = request.getRequestURI();
		//컨텍스트명이 가변적이기 때문에 이렇게 자르자! substring 리턴값 int니까 .length 붙이고
		command = command.substring(request.getContextPath().length());	//프로젝트명 다음부터 시작하게끔 짜른다.
		System.out.println(command);
		System.out.println();
		
		//이렇게 if문 쓰는 방법도 있고~ -> 팩토리 쓰자 그냥 너무 복잡해짐 -> 팩토리-해쉬맵으로~
//		if(command.equals("/login.do")) {
//			
//		}else if(command.equals("/main.do")) {
//			
//		}
		
		//명령어 호출된 값(ex.로그인페이지 등) 가져온다.
		CommandHandler handler = commandFactory.getCommandHandler(command);
		//가져온 핸들러의 프로세스 실행.
		//url에 지 맘대로 입력하면 handler에 null값 입력되므로 널포인터익셉션 발생할 수 있다. 예외처리 해주자!
		String view = null;
		
		if(handler != null) {
			view = handler.process(request, response);		//다형성에 의해 상속받은 각 핸들러의 프로세스를 실행.
		}else {
			System.out.println("[경고] 명령어에 매핑된 객체가 없습니다.");
		}
		
		//뷰 2가지 경우 : 리다이렉트 있는 거, 없는 거
		if(view != null) {
			if(view.startsWith("redirect:")) {	//startWith : 문자열의 시작이 이거냐하는 API
				
				view = view.substring("redirect:".length());	//문자열 안짜르고 그냥 넣으면 진짜 url에 redirect 붙여버림.
				response.sendRedirect(view);
				
			}else {		//리다이렉트 없으면 포워딩으로!
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
				
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
