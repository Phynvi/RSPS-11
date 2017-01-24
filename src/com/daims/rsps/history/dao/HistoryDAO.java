package com.daims.rsps.history.dao;

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


public class HistoryDAO {
	 
	public List<Map<String, Object>> getHistoryList(Map<String, Object> params) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = JdbcConnectionUtil.getConnection();
		int limit = (int) params.get("limit");
		int startIndex = (int) params.get("startIndex");
		String sql = "select * from(select HISTORY_ID, P_TYPE, U_TYPE, VS_RESULT , TO_CHAR(REG_DATE, 'YYYY-MM-DD HH:mm:ss') as REG_DATE , rownum as rn from RSPS_HISTORY order by HISTORY_ID desc)a where a.rn >="+startIndex+" and a.rn <="+limit;
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
	            // list에 저장
	            list.add(map);
	        }
		 JdbcConnectionUtil.closeConnection(conn, stmt, rs);
		return list; 
	}
	
	public int getHistoryCnt(Map<String, Object> params) throws SQLException{
		Connection conn = JdbcConnectionUtil.getConnection();
		
		String sql = "SELECT COUNT(HISTORY_ID) AS CNT FROM RSPS_HISTORY";
		
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			cnt =rs.getInt(1);
		}
		JdbcConnectionUtil.closeConnection(conn, stmt, rs);
		return cnt;
	}
}