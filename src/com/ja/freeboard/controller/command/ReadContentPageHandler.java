package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ja.freeboard.model.*;
import com.ja.freeboard.vo.*;

public class ReadContentPageHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		//겟파라미터는 무조건 스트링(인트 없음) -> 형변환 필요.
		int b_no = Integer.parseInt(request.getParameter("b_no"));		
		
		BoardVo boardVo = new BoardDao().selectByNo(b_no);
		
		MemberVo memberVo = new MemberDao().selectByNo(boardVo.getM_no());
		
		ContentDataVo contentDataVo = new ContentDataVo(memberVo, boardVo);
		
		//b_no, m_no 리퀘스트객체에 담음.
		request.setAttribute("contentDataVo", contentDataVo);
		
		
		return "/WEB-INF/view/read_content_page.jsp";
	}

}
