package com.ja.freeboard.model;

import java.util.*;
import java.util.Date;
import java.sql.*;
import com.ja.freeboard.vo.*;

//board 테이블 DAO 데이터베이스 연동

public class BoardDao {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "SCOTT";
	private static final String PASSWORD = "TIGER";
	
	public ArrayList<BoardVo> selectAll() {			//리턴타입 어레이리스트
		
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		
		
		String query = "SELECT * FROM FB_Board ORDER BY b_no DESC";	//값에만 ? 들어갈 수 있다!
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		//select니까 ResultSet 필요하다.
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstm = conn.prepareStatement(query);
			
			rs = pstm.executeQuery();			//select니까 executeQuery!
			
			//로직 - 리턴만 해주고 핸들러에서 처리
			while(rs.next()) {
				
				int b_no = rs.getInt("b_no");
				int m_no = rs.getInt("m_no");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				Date b_writedate = rs.getDate("b_writedate");
				
				BoardVo boardVo = new BoardVo(b_no, m_no, b_title, b_content, b_writedate);
				
				list.add(boardVo);		//리스트에 데이터 담는다.
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
		
		
		return list;
		
	}
	
}
