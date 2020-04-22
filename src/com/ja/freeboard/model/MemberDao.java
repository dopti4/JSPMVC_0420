package com.ja.freeboard.model;

import java.sql.*;
import java.util.*;

import com.ja.freeboard.vo.*;

//DAO객체 : 테이블당 하나씩 생성하는게 정석

public class MemberDao {

	//저번에 했던 거 상수로 밖에 빼놓자(어차피 안 바뀌잖어).
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";
	
	
	public void insert(String m_id, String m_pw, String m_nick, String m_phone) {	//메소드 명명법 주의, joinMember 요런건 쫌.. 정확한 의미가 아니다.
		
		String query = "INSERT INTO FB_Member VALUES(FB_Member_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//시작!
		try {
			
			//클래스를 동적으로 로드하겠다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("java.lang.System");
			
			//oracle.jdbc.driver.OracleDriver d; 	이것도 가능(즉, 클래스를 한번이라도 사용하면 됨).
			
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);	//prepareStatement 사용 -> 불완전한 query 넣어주고 ??에 setString으로 순서대로 입력.
			pstm.setString(1, m_id);				//자동으로 순서에 맞게 물음표에 값 넣어준다.
			pstm.setString(2, m_pw);
			pstm.setString(3, m_nick);
			pstm.setString(4, m_phone);
			
			pstm.executeUpdate();					//insert니까 executeUpdate
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			if(pstm != null) {
				try {
					pstm.close();			//close 해주려면 트라이-캐치 또 써야한다.
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public MemberVo selectByIdAndPw(String id, String pw) {		//이름 정확히하자. 직관적으로, 일관성있게
		
		MemberVo memberVo = null;	//이름 짓기 귀찮을 때 그냥 클래스명 앞에 소문자로..
		
		String query = "SELECT * FROM FB_Member WHERE m_id = ? AND m_pw = ?";	//값에만 ? 들어갈 수 있다!
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		//select니까 ResultSet 필요하다.
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, id);	//?에 들어갈 값이 숫자면 setInt
			pstm.setString(2, pw);
			
			rs = pstm.executeQuery();			//select니까 executeQuery!
			
			//로직 - 리턴만 해주고 핸들러에서 처리
			if(rs.next()) {
				
				int m_no = rs.getInt("m_no");
				String m_id = rs.getString("m_id");
				String m_pw = rs.getString("m_pw");
				String m_nick = rs.getString("m_nick");
				String m_phone = rs.getString("m_phone");
				java.util.Date m_joindate = rs.getDate("m_joindate");	//util, sql 둘 다 임포트 했기 때문에 어떤거 쓸건지 정해줘야함.
				
				memberVo = new MemberVo(m_no, m_id, m_pw, m_nick, m_phone, m_joindate);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(pstm != null) {
				try {
					pstm.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return memberVo;
		
	}
	
	public MemberVo selectByNo(int no) {
		
		MemberVo memberVo = null;
		
		String query = "SELECT * FROM FB_Member WHERE m_no = ?";	//값에만 ? 들어갈 수 있다!
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		//select니까 ResultSet 필요하다.
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, no);	//?에 들어갈 값이 숫자면 setInt
			
			rs = pstm.executeQuery();			//select니까 executeQuery!
			
			//로직 - 리턴만 해주고 핸들러에서 처리
			if(rs.next()) {				//PK니까 어차피 한개만 나옴 -> while 안씀.
				
				int m_no = rs.getInt("m_no");
				String m_id = rs.getString("m_id");
				String m_pw = rs.getString("m_pw");
				String m_nick = rs.getString("m_nick");
				String m_phone = rs.getString("m_phone");
				java.util.Date m_joindate = rs.getDate("m_joindate");	//util, sql 둘 다 임포트 했기 때문에 어떤거 쓸건지 정해줘야함.
				
				memberVo = new MemberVo(m_no, m_id, m_pw, m_nick, m_phone, m_joindate);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(pstm != null) {
				try {
					pstm.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		return memberVo;
		
	}
	
	
}
