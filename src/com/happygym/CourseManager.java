package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";

		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 프로그램 등록
	public boolean registerCourse(Teacher teacher, Course course) {

		Connection conn = getConnect();

		String msql = "INSERT INTO tbl_courses(course_id, course_name, t_id, schedule, capacity, time, target) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(msql);
			psmt.setInt(1, course.getCourseId());
			psmt.setString(2, course.getCourseName());
			psmt.setString(3, teacher.getT_id());
			psmt.setString(4, course.getSchedule());
			psmt.setInt(5, course.getCapacity());
			psmt.setString(6, course.getTime());
			psmt.setString(7, course.getTarget());
			int r = psmt.executeUpdate();
			if (r > 0) {

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 프로그램 수정
	public boolean updateCourse(Teacher teacher, Course course) {
		Connection conn = getConnect();
		String sql = "UPDATE tbl_courses SET " + "course_name = NVL(?, course_name), " + "t_id = NVL(?, t_id), "
				+ "schedule = NVL(?, schedule), " + "capacity = NVL(?, capacity), " + "time = NVL(?, time), "
				+ "target = NVL(?, target) " + "WHERE course_id = ?"; // course_id는 문자열이 아닌 정수로 설정

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, course.getCourseName()); // course_name
			psmt.setString(2, teacher.getT_id()); // t_id
			psmt.setString(3, course.getSchedule()); // schedule
			psmt.setInt(4, course.getCapacity()); // capacity
			psmt.setString(5, course.getTime()); // time
			psmt.setString(6, course.getTarget()); // target
			psmt.setInt(7, course.getCourseId()); // course_id

			int u = psmt.executeUpdate();
			return u > 0; // 업데이트 성공 여부 반환
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
			return false; // 실패
		} finally {
			try {
				if (conn != null)
					conn.close(); // 연결 종료
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 프로그램 삭제
	public boolean deleteCourse(String courseId) {
		Connection conn = getConnect();
		String sql = "delete from tbl_courses\r\n" + "where course_id='" + courseId + "'";
		try {
			Statement dstmt = conn.createStatement();
			int d = dstmt.executeUpdate(sql); // 쿼리
			if (d > 0) {
				return true; // 삭제성공.
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	// 목록 프로그램 명으로 조회
	public Course selectCourseName(String courseName) {
		Connection conn = getConnect();
		String sql = "select * from tbl_courses where course_name=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, courseName);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				Course course = new Course();
				Teacher teacher = new Teacher();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setTeacher(teacher);
				course.setSchedule(rs.getString("schedule"));
				course.setCapacity(rs.getInt("capacity"));
				course.setTime(rs.getString("time"));
				course.setTarget(rs.getString("target"));
				return course;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// 목록 강사명으로 조회
	public List<Course> list(String teacherName) {
		List<Course> list = new ArrayList<Course>();
		Connection conn = getConnect();

		String sql = "SELECT c.course_id, c.course_name,c.schedule,c.time,c.target,c.capacity\r\n"
				+ "FROM tbl_courses c\r\n" + "JOIN tbl_teacher t ON c.t_id = t.tId\r\n" + "WHERE t.tName = '?'";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, teacherName);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setSchedule(rs.getString("schedule"));
				course.setTime(rs.getString("time"));
				course.setTarget(rs.getString("target"));
				course.setCapacity(rs.getInt("capacity"));
				list.add(course);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;

	}

	// 목록 전제초회
	List<Course> getAllList(String all) {
		List<Course> gAList = new ArrayList<Course>();
		Connection conn = getConnect();
		String sql = "SELECT c.*, t.tId\r\n"
				+ "FROM tbl_courses c\r\n"
				+ "LEFT JOIN tbl_teacher t ON c.t_id = t.tId\r\n"
				+ "ORDER BY c.course_id ASC";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
			
				Course course = new Course();
				Teacher teacher = new Teacher();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				teacher.setT_id(rs.getString("tId")); // tId로 수정	
				course.setTeacher(teacher); // Course 객체에 Teacher 객체 추가
				course.setSchedule(rs.getString("schedule"));
				course.setCapacity(rs.getInt("capacity"));
				course.setTime(rs.getString("time"));
				course.setTarget(rs.getString("target"));
				gAList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gAList;
	}

}
