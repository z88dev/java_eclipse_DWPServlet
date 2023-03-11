package com.prog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// 	alwaysdata	
	private static String jdbcURL = "jdbc:mysql://mysql-dwp222.alwaysdata.net:3306/dwp222_db";
	private static String jdbcUsername = "dwp222";
	private static String jdbcPassword = "dwpass";	

/*	MySQL WorkBench
	private static String jdbcURL = "jdbc:mysql://localhost:3306/serveurdb";
	private static String jdbcUsername = "dbuser";
	private static String jdbcPassword = "dbpass";
*/			
	private static Connection jdbc_con = null;
		   	 
	public static Connection getConnection() throws SQLException {
		try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   jdbc_con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);		  
		} catch (SQLException e) {
		   e.printStackTrace();
		} catch (ClassNotFoundException e) {
		   e.printStackTrace();
		}
		 return jdbc_con;
	}  // end getConnection
		
	public static void disconnect() throws SQLException {
	   if (jdbc_con != null && !jdbc_con.isClosed()) {
	      jdbc_con.close();
	   }
	}  // end disconnect	
}  // end DBUtil


