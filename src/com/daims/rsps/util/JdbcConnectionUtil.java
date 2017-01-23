package com.daims.rsps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionUtil {
	
	private static  String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static  String id = "yul";
	private static String pw = "1234";
	
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
	
	public static void closeConnection( Connection conn,PreparedStatement  stmt,  ResultSet rs){
		try{
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void closeConnection( Connection conn,PreparedStatement  stmt){
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
