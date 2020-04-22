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
	
	//글 삭제~
	public void delete(int no) {
		
		String query = "DELETE FROM FB_Board WHERE b_no = ?";
		
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
			pstm.setInt(1, no);				//자동으로 순서에 맞게 물음표에 값 넣어준다.
			
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
	
	//글 수정~
	public void update(int no, String title, String content) {
		
		String query = "UPDATE FB_Board SET b_title = ?, b_content = ? WHERE b_no = ?";
		
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
			pstm.setString(1, title);
			pstm.setString(2, content);
			pstm.setInt(3, no);				//자동으로 순서에 맞게 물음표에 값 넣어준다.
			
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
	
	//글 쓰기~
	public void insert(int memberNo, String title, String content) {
		
		String query = "INSERT INTO FB_Board VALUES(fb_board_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
		
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
			pstm.setInt(1, memberNo);
			pstm.setString(2, title);
			pstm.setString(3, content);				//자동으로 순서에 맞게 물음표에 값 넣어준다.
							
			
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
	
	//글 보기~
	public BoardVo selectByNo(int no) {
		
		BoardVo boardVo = null;
		
		
		String query = "SELECT * FROM FB_Board WHERE b_no = ?";	//값에만 ? 들어갈 수 있다!
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;		//select니까 ResultSet 필요하다.
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstm = conn.prepareStatement(query);
			pstm.setInt(1, no);
			
			rs = pstm.executeQuery();			//select니까 executeQuery!
			
			//로직!
			if(rs.next()) {
				int b_no = rs.getInt("b_no");
				int m_no = rs.getInt("m_no");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				Date b_writedate = rs.getDate("b_writedate");
				
				boardVo = new BoardVo(b_no, m_no, b_title, b_content, b_writedate);
				//boardVo = new BoardVo(rs.getInt("b_no"), rs.getInt("m_no"), rs.getString("b_title"), rs.getString("b_content"), rs.getDate("b_writedate"));
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
		
		
		
		return boardVo;
		
	}
	
}
