package com.ja.freeboard.model;

import java.sql.*;
import java.util.*;

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
			
			pstm.executeUpdate();					//insert니까 update
					
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
	
}
