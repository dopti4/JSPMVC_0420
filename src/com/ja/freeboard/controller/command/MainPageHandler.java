package com.ja.freeboard.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ja.freeboard.model.*;
import com.ja.freeboard.vo.*;
import java.util.*;

//메인 페이지 처리

public class MainPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<ContentDataVo> contentList = new ArrayList<ContentDataVo>();		//두개 합친 거 담을거야.
		
		ArrayList<BoardVo> boardList = new BoardDao().selectAll();		//바로 실행
		
		MemberDao memberDao = new MemberDao();
		
		for(BoardVo boardVo : boardList) {		//배열에서 하나씩 뽑아서 넣어준다.
			
			MemberVo memberVo = memberDao.selectByNo(boardVo.getM_no());
			
			//담을 객체 생성해주고 - ContentDataVo = board + member
			
			ContentDataVo contentDataVo = new ContentDataVo(memberVo, boardVo);
			
			contentList.add(contentDataVo);		//합친 거 추가!
			
		}
		
		request.setAttribute("contentList", contentList);		//리스트를 리퀘스트객체에 키와 값으로 담는다.
		
		return "/WEB-INF/view/main_page.jsp";
	}

}
