package com.ja.freeboard.controller;

import com.ja.freeboard.controller.command.*;
import java.util.*;


//./Java Resources/src/com.ja.freeboard.controller/CommandFactory


public class CommandFactory {
	//호출될 때마다 각 인스턴스 찍어낸다.
	
	//원래 팩토리패턴의 의도 -> 호출될 때마다 생성, 소멸 반복
//	public CommandHandler getCommandHandler(String command) {
//		
//		if(command.equals("/login_page.do")) {
//			return new LoginPageHandler();
//		}
//	}
	
	
	//그냥 한번에 생성시키고 그때그때 호출된 값 뽑아내서 쓰자 이말이야~
	private HashMap<String, CommandHandler> commandMap;	//내부에서 리턴할 용도로만 쓸거니까 private
	
	public CommandFactory() {
		commandMap = new HashMap<String, CommandHandler>(); 
		
		commandMap.put("/login_page.do", new LoginPageHandler());
		commandMap.put("/join_member_page.do", new JoinMemberPageHandler());
		commandMap.put("/join_member_process.do", new JoinMemberProcessHandler());
		commandMap.put("/login_process.do", new LoginProcessHandler());
		commandMap.put("/main_page.do", new MainPageHandler());
		commandMap.put("/logout_process.do", new LogoutProcessHandler());
		commandMap.put("/write_content_page.do", new WriteContentPageHandler());
		commandMap.put("/write_content_process.do", new WriteContentProcessHandler());
		commandMap.put("/read_content_page.do", new ReadContentPageHandler());
		commandMap.put("/delete_content_process.do", new DeleteContentProcessHandler());
		commandMap.put("/update_content_page.do", new UpdateContentPageHandler());
		commandMap.put("/update_content_process.do", new UpdateContentProcessHandler());
		
		
	}
	
	public CommandHandler getCommandHandler(String command) {
		
		return commandMap.get(command);
	}
	
	
}
