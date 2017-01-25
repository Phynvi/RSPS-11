package com.daims.rsps.board.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.daims.rsps.util.JdbcConnectionUtil;


public class BoardDAO {
	 
	public List<Map<String, Object>> getBoardList() throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = JdbcConnectionUtil.getConnection();
		String sql = "SELECT BOARD_ID, TITLE, CONTENT , WRITER, TO_CHAR(WRITE_DATE, 'YYYY-MM-DD')as WRITE_DATE FROM RSPS_BOARD ORDER BY BOARD_ID ASC";
		PreparedStatement stmt =    conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ResultSetMetaData metaData = rs.getMetaData();
		 int sizeOfColumn = metaData.getColumnCount();
		 
		 Map<String, Object> map;
		 String column;
		 while (rs.next())
	        {
	            map = new HashMap<String, Object>();
	            for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++)
	            {
	                column = metaData.getColumnName(indexOfcolumn + 1);
	                map.put(column, rs.getString(column));
	            }
	            list.add(map);
	        }
		 JdbcConnectionUtil.closeConnection(conn, stmt, rs);
		return list; 
	}
	
	public int addBoard(String title, String writer, String content) throws SQLException {
	    int cnt = 0; 
		Connection conn = JdbcConnectionUtil.getConnection();
	    String sql = "INSERT INTO RSPS_BOARD(WRITER, TITLE, CONTENT, BOARD_ID, WRITE_DATE) VALUES(?, ?, ?, SEQ_BOARD.NEXTVAL, SYSDATE)";
	    PreparedStatement stmt =    conn.prepareStatement(sql);
	    stmt.setString(1, writer);
	    stmt.setString(2, title);
	    stmt.setString(3, content);
	    stmt.executeQuery();
	    JdbcConnectionUtil.closeConnection(conn, stmt);
		return cnt;
	}
}