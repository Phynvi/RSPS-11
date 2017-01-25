package com.daims.rsps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnectionUtil {
	
	private static  String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static  String id = "****";
	private static String pw = "****";
	
	static {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			 conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return conn;
	}
	
	public static void closeConnection( Connection conn,PreparedStatement  stmt,  ResultSet rs) throws SQLException{
		try{
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			if(rs !=null){
				rs.close();
			}
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
	}
	public static void closeConnection( Connection conn,PreparedStatement  stmt) throws SQLException{
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			if(stmt !=null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
	}
}
