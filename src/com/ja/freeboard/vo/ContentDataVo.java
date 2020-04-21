package com.ja.freeboard.vo;

//멤버VO + 보드VO

public class ContentDataVo {
	//객체 안 객체
	private MemberVo memberVo;
	private BoardVo boardVo;
	
	public ContentDataVo() {
		super();
	}

	public ContentDataVo(MemberVo memberVo, BoardVo boardVo) {
		super();
		this.memberVo = memberVo;
		this.boardVo = boardVo;
	}

	public MemberVo getMemberVo() {
		return memberVo;
	}

	public void setMemberVo(MemberVo memberVo) {
		this.memberVo = memberVo;
	}

	public BoardVo getBoardVo() {
		return boardVo;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}
	
	
	
}
