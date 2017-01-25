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
	 
	/**
	 * 가위바위보 이력 List를 가져온다.
	 * @param params
	 * @return List<Map<String, Object>> list
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getHistoryList(Map<String, Object> params) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = JdbcConnectionUtil.getConnection();
		int limit = (int) params.get("limit");
		int startIndex = (int) params.get("startIndex");
		String sql = "SELECT * FROM(SELECT HISTORY_ID, P_TYPE, U_TYPE, VS_RESULT , TO_CHAR(REG_DATE, 'YYYY-MM-DD HH:mm:ss') as REG_DATE , ROWNUM AS RN FROM RSPS_HISTORY ORDER BY HISTORY_ID DESC)A WHERE A.RN >="+startIndex+" AND A.RN <="+limit + " ORDER BY A.RN ASC";
		PreparedStatement stmt =    conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		ResultSetMetaData metaData = rs.getMetaData();
		 int sizeOfColumn = metaData.getColumnCount();
		 
		 Map<String, Object> map;
		 String column;
		 String vs_result="";
		 int col_vs_result=0;
		 while (rs.next())
	        {
	            map = new HashMap<String, Object>();
	            for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++)
	            {
	                column = metaData.getColumnName(indexOfcolumn + 1);
	                if(column.equals("VS_RESULT")){
	                	col_vs_result  = Integer.parseInt(rs.getString(column).trim()); 
        				if(col_vs_result == 0){
	                		vs_result = "비겼음";
	                	}
	                	if(col_vs_result == -1){
	                		vs_result = "졌음";
	                	}
	                	if(col_vs_result == 2){
	                		vs_result = "졌음";
	                	}
	                	if(col_vs_result == 1){
	                		vs_result = "이겼음";
	                	}
	                	if(col_vs_result == -2){
	                		vs_result = "이겼음";
	                	}
	                	map.put(column,vs_result);
	                }else{
	                	map.put(column, rs.getString(column));
	                }
	            }
	            list.add(map);
	            
	        }
		 JdbcConnectionUtil.closeConnection(conn, stmt, rs);
		return list; 
	}
	
	/**
	 * 총 가위바위보 게임수를 가지고 온다
	 * @param params
	 * @return int cnt
	 * @throws SQLException
	 */
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