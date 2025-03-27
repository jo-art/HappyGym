package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentsJdbc {
	//커넥션 객체 생성 .
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
	//수강신청등록 쿼리
	
	// 수강과목 존재 여부 확인 후 수강신청 등록 쿼리
	public boolean addEnrollProgram(Enrollments enrollments) {
		System.out.println(enrollments);
	    Connection conn = getConnect();
	   String sql="INSERT INTO enrollments(enrollment_id, MEM_ID, course_id, enrollment_date, status)  "
	   		+ "SELECT  ?, ?, ?, ?, ?  "
	   		+ "FROM dual  "
	   		+ "WHERE NOT EXISTS ( "
	   		+ "    SELECT 1 FROM enrollments "
	   		+ "    WHERE  course_id = ? "
	   		+ ")";
	    
	    try {
	    	
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        psmt.setString(1, enrollments.getEnrollmentid());
	        psmt.setString(2, enrollments.getMemid()); // Member 정보 사용
	        psmt.setInt(3, enrollments.getCourseId()); // Course 정보 사용
	        psmt.setTimestamp(4, enrollments.getEnrollmentDate());
	        psmt.setString(5, enrollments.getStatus());
	        psmt.setInt(6, enrollments.getCourseId()); // course_id 확인

	        int r = psmt.executeUpdate();
	        return r > 0; // 성공적으로 추가되었으면 true 리턴
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	    return false; // 추가 실패
	}


	
	//수강과목 아이디 여부 확인 후 수강신청 테이블에서 삭제
	public boolean deleteEnrollProgram(Enrollments enrollments) {
	    Connection conn = getConnect();
	    
	    String sql = "DELETE FROM enrollments "
	               + "WHERE course_id = ?";

	    try {
	        PreparedStatement psmt = conn.prepareStatement(sql);

	        psmt.setInt(1, enrollments.getCourseId()); 

	        int r = psmt.executeUpdate();
	        return r > 0; // 성공적으로 삭제되었으면 true 리턴
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) conn.close(); // 연결 해제
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false; // 삭제 실패
	}

   
	//신쳥내역리스트 조회 쿼리
	public List<Enrollments> getEnrollments(String memID){
		List<Enrollments> list =new ArrayList<Enrollments>();
		Connection conn= getConnect();
		String sql="SELECT DISTINCT * FROM enrollments WHERE MEM_ID = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, memID);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Enrollments enroll = new Enrollments();
				enroll.setEnrollment_id(rs.getString("enrollment_id"));
				enroll.setMemid(rs.getString("mem_id"));
				
				enroll.setCourseId(rs.getInt("course_id"));
				enroll.setEnrollmentDate(rs.getTimestamp("enrollment_date"));
				
				enroll.setStatus(rs.getString("status"));
				list.add(enroll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (conn != null)
					conn.close(); // 연결 종료
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
