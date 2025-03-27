package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GymJdbc {
	//연결 커넥션
	Connection getConnect() {

		String url="jdbc:oracle:thin:@192.168.0.32:1521:xe";

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
