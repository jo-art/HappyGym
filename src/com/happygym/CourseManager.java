package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CourseManager {
	Connection getConnect() {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userId="scott";
		String userPw="tiger";
		
		try {
			Connection conn = DriverManager.getConnection(url,userId,userPw);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
}
