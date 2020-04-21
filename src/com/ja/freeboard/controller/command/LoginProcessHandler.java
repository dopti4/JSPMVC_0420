package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ja.freeboard.model.MemberDao;
import com.ja.freeboard.vo.MemberVo;

//로그인 처리

public class LoginProcessHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		
		MemberVo memberVo = new MemberDao().selectByIdAndPw(m_id, m_pw);
		
		if(memberVo != null) {
			//로그인 성공 - 세션에 담고 리다이렉트 처리
			request.getSession().setAttribute("sessionUserInfo", memberVo);		//세션에 통째로 넣음.
			
			return "redirect:./main_page.do";		//리다이렉트로 한 이유 : 메인페이지핸들러 코드 실행해야하니까(배열)
			//return "/WEB-INF/view/main_page.jsp";	//포워딩으로 하면 코드 실행없이 jsp로 간다
			
		}else {
			//로그인 실패
			return "/WEB-INF/view/login_fail.jsp";		//실패시 이쪽으로 포워딩
		}
		
	}

}
