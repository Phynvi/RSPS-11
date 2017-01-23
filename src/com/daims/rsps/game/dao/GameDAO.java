package com.daims.rsps.game.dao;

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


public class GameDAO {
	 
	public int addGame(String p_type, String u_type, String vs_result) throws SQLException {
	    int cnt = 0; 
		
		Connection conn = JdbcConnectionUtil.getConnection();
//	    String sql = "insert into RSPS_HISTORY(P_TYPE, U_TYPE, VS_RESULT,  HISTORY_ID,REG_DATE)VALUES ('"+p_type+"','"+u_type+"','"+vs_type+"','3',SYSDATE );";
	    String sql = "insert into RSPS_HISTORY(P_TYPE, U_TYPE, VS_RESULT,  HISTORY_ID,REG_DATE)VALUES ( ?, ?, ?, ?, SYSDATE )";
	    PreparedStatement stmt =    conn.prepareStatement(sql);
	    stmt.setString(1, p_type);
	    stmt.setString(2, u_type);
	    stmt.setString(3, vs_result);
	    stmt.setInt(4, 8);
	    stmt.executeQuery();
	    JdbcConnectionUtil.closeConnection(conn, stmt);
	    
		return cnt;

	/*
	    //TODO select 일경우
	    ResultSet rs = pstmt.executeXX();
	*/
	}

	public List<Map> getHistory() throws SQLException{
		List<Map> list = new ArrayList<Map>();
		Connection conn = JdbcConnectionUtil.getConnection();
		String sql = "SELECT HISTORY_ID, P_TYPE, U_TYPE, VS_RESULT FROM RSPS_HISTORY ORDER BY HISTORY_ID ASC";
		PreparedStatement stmt =    conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ResultSetMetaData metaData = rs.getMetaData();
		 int sizeOfColumn = metaData.getColumnCount();
		 
		 Map<String, Object> map;
		 
		 String column;
		 while (rs.next())
	        {
	            // 내부에서 map을 초기화
	            map = new HashMap<String, Object>();
	            // Column의 갯수만큼 회전
	            for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++)
	            {
	                column = metaData.getColumnName(indexOfcolumn + 1);
	                // map에 값을 입력 map.put(columnName, columnName으로 getString)
	                map.put(column, rs.getString(column));
	            }
	            // list에 저장
	            list.add(map);
	        }
		return list; 
	}
	
}
