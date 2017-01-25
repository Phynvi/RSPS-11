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
	 
	/**
	 * 게임을 할때마다 해당 결과를 저장한다.
	 * @param pcType
	 * @param userType
	 * @param vsResult
	 * @return int cnt
	 * @throws SQLException
	 */
	public int addGame(String pcType, String userType, String vsResult) throws SQLException {
	    int cnt = 0; 
		Connection conn = JdbcConnectionUtil.getConnection();
	    String sql = "INSERT INTO RSPS_HISTORY(P_TYPE, U_TYPE, VS_RESULT, HISTORY_ID, REG_DATE) VALUES (?,?,?, SEQ_HISTORY.NEXTVAL, SYSDATE )";
	    PreparedStatement stmt =    conn.prepareStatement(sql);
	    stmt.setString(1, pcType);
	    stmt.setString(2, userType);
	    stmt.setString(3, vsResult);
	    stmt.executeQuery();
	    JdbcConnectionUtil.closeConnection(conn, stmt);
	    
		return cnt;
	}
	
}
