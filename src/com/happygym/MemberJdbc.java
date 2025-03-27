package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberJdbc {

	// 커넥션 생성 메소드

	Connection getConnect() {

		String url = "jdbc:oracle:thin:@192.168.0.32:1521:xe";

		String userId = "scott";

		String userPw = "tiger";
		Connection conn = null;
		try {

			conn = DriverManager.getConnection(url, userId, userPw);

			return conn;

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return null;

	}

	// 수강생 회원 로그인처리
	public Member mlogin(String id, String pw) {

		// id, pw 조회

		Connection conn = getConnect();

		String sql = "select * from tbl_member " + "where memId=? " + "and memPw=?";

		try {

			PreparedStatement psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);

			psmt.setString(2, pw);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {

				Member mem = new Member(rs.getString("memId"), rs.getString("memPw"), rs.getString("memName"));

				return mem;

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

		return null; // 조회결과 없으면

	}

//관리자 로그인 처리

	// 회원등록처리
	public boolean insert(Member member) {

		Connection conn = getConnect();
		String sql = "insert into tbl_member(memId,memPw,memName,memAge,memPnum,memAddr,memHeight,memWeight )"
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMem_id());
			psmt.setString(2, member.getMem_pw());
			psmt.setString(3, member.getMem_name());
			psmt.setInt(4, member.getMem_age());
			psmt.setString(5, member.getMem_pnum());
			psmt.setString(6, member.getMem_address());
			psmt.setString(7, member.getMem_height());
			psmt.setString(8, member.getMem_weight());
			int r = psmt.executeUpdate();
			if (r > 0) {

				return true;
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
		return false; // 등록 실패
	}
	//선생  정보 수정 쿼리
	
	
	// 회원 정보 수정 쿼리
	public boolean updateMemberInfo(Member member) {
		Connection conn = getConnect();
		String sql ="UPDATE tbl_member \r\n"
				+ "SET MEMID=NVL(?,MEMID),\r\n"
				+ "MEMPW=NVL(?,MEMPW),\r\n"
				+ "MEMNAME=NVL(?,MEMNAME),\r\n"
				+ "MEMAGE=NVL(?,MEMAGE),\r\n"
				+ "MEMPNUM=NVL(?,MEMPNUM),\r\n"
				+ "MEMADDR=NVL(?,MEMADDR),\r\n"
				+ "MEMHEIGHT=NVL(?,MEMHEIGHT),\r\n"
				+ "MEMWEIGHT=NVL(?,MEMWEIGHT)\r\n"
				+ "WHERE MEMID=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1,member.getMem_id());
			psmt.setString(2, member.getMem_pw());
			psmt.setString(3, member.getMem_name());
			psmt.setInt(4, member.getMem_age());
			psmt.setString(5, member.getMem_pnum());
			psmt.setString(6, member.getMem_address());
			psmt.setString(7,member.getMem_height());
			psmt.setString(8, member.getMem_weight());
			psmt.setString(9,member.getMem_id());
			
			int u = psmt.executeUpdate();
			return u > 0; // 업데이트 성공 여부 반환
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close(); // 연결 종료
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//선생 수정 
	public boolean updateTeacherInfo(Teacher teacher) {
		Connection conn = getConnect();
		String sql="UPDATE tbl_teacher \r\n"
				+ "SET TID=NVL(?,TID),\r\n"
				+ "TPW=NVL(?,TPW),\r\n"
				+ "TNAME=NVL(?,TNAME),\r\n"
				+ "AGE=NVL(?,AGE),\r\n"
				+ "TPNUM=NVL(?,TPNUM),\r\n"
				+ "HIRE_DATE=NVL(?,HIRE_DATE)\r\n"
				+ "WHERE TID=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, teacher.getT_id());
			psmt.setString(2, teacher.getT_pw());
			psmt.setString(3,teacher.getT_name());
			psmt.setInt(4, teacher.getT_age());
			psmt.setString(5, teacher.getT_Pnum());
			psmt.setString(6, teacher.getHire_date());
			psmt.setString(7, teacher.getT_id());
			int u = psmt.executeUpdate();
			return u > 0; // 업데이트 성공 여부 반환
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close(); // 연결 종료
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 회원 삭제 쿼리
	public boolean deleteMemberInfo(Member member) {
		Connection conn = getConnect();
		String sql = "delete from tbl_member where MEMID=?";

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMem_id());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
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
	//선생 삭제 쿼리
	public boolean deleteTeacherInfo(Teacher teacher) {
		Connection conn = getConnect();
		String sql = "delete from tbl_teacher where TID=?";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, teacher.getT_id());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
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

	
	// 로그인한 회원의 아이디 , 이름 가져오기
	public Member getMemberFromOjdbc(String memberId) {
		Member member = null;
		Connection conn = getConnect();
		String sql = "SELECT * FROM tbl_member where MEMID=?"; // 회원정보를 조회하는 쿼리
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberId);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				member = new Member(); // 멤버 객체 인스턴스 생성
				member.setMem_id("MEMID");
				member.setMem_name("MEMNAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close(); // 연결 종료
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	// 강사 로그인 처리
	public Teacher tlogin(String id, String pw) {
		Connection conn = getConnect();

		String sql = "select * from tbl_teacher " + "where tId=? " + "and tPw=?";

		try {

			PreparedStatement psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);

			psmt.setString(2, pw);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {

				Teacher teacher = new Teacher(rs.getString("tId"), rs.getString("tPw"), rs.getString("tName"));

				return teacher;

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

		return null; // 조회결과 없으면
	}

	// 강사 등록 처리
	public boolean Teacherinsert(Teacher teacher) {

		Connection conn = getConnect();
		String sql = "INSERT INTO tbl_teacher(tId, tPw, tName, tPnum, hire_date,Age) VALUES (?, ?, ?, ?, ?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, teacher.getT_id());
			psmt.setString(2, teacher.getT_pw());
			psmt.setString(3, teacher.getT_name());
			psmt.setString(4, teacher.getT_Pnum());
			psmt.setString(5, teacher.getHire_date());
			psmt.setInt(6, teacher.getT_age());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
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

	// 관리자 로그인
	public Adminer Alogin(String id, String pw) {
		Connection conn = getConnect();

		String sql = "select * from tbl_admin " + "where admin_id=? " + "and admin_pw=?";

		try {

			PreparedStatement psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);

			psmt.setString(2, pw);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {

				Adminer adminer = new Adminer(rs.getString("admin_id"), rs.getString("admin_pw"),
						rs.getString("admin_name"));

				return adminer;

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

		return null; // 조회결과 없으면
	}

	// 관리자 등록처리
	public boolean adminerinsert(Adminer admin) {
		Connection conn = getConnect();
		String sql = "INSERT INTO tbl_admin(admin_id,admin_pw,admin_name) VALUES(?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, admin.getAdm_id());
			psmt.setString(2, admin.getAdm_pw());
			psmt.setString(3, admin.getAdm_name());
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
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

	// 회원등록시 아이디 존재유무 확인
	public boolean isIdExists(String id) {
		// TODO Auto-generated method stub
		Connection conn = getConnect();
		String msql = "select memId from tbl_member where memId=?";
		String tsql = "select tId from tbl_teacher where tId=?";
		String asql = "select admin_id from tbl_admin where admin_id=?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// tbl_member에서 id 확인
			psmt = conn.prepareStatement(msql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true; // tbl_member에서 id가 존재 하면 트루
			}
			psmt = conn.prepareStatement(tsql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true; // tbl_teacher에서 id가 존재
			}
			psmt = conn.prepareStatement(asql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true; // tbl_adminer에서 id가 존재
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

	// 아이디, 비밀번호 hasymap으로 저장
	public List<Map<String, String>> memberList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = getConnect();
		String sql = "select * from tbl_member";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("memId", rs.getString("memId"));
				map.put("memPw", rs.getString("memPw"));
				map.put("memName", rs.getString("memName"));
				list.add(map);
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
	//회원 목록 조회
	// 회원 목록 조회
	public List<Member> MList() {
	    List<Member> MList = new ArrayList<Member>();
	    Connection conn = getConnect();
	    String sql = "SELECT * FROM tbl_member"; // 테이블 이름이 tbl_member로 가정
	    try {
	        PreparedStatement psmt = conn.prepareStatement(sql);
	        ResultSet rs = psmt.executeQuery();
	        while (rs.next()) {
	            // ResultSet에서 데이터 읽기
	            String memberId = rs.getString("memid"); // 회원 ID
	            String name = rs.getString("memname"); // 회원 이름
	            
	            // Member 객체 생성
	            Member member = new Member();
	           member.setMem_id(memberId);
	           member.setMem_name(name);
	           member.setMem_address(rs.getString("MEMADDR"));
	           member.setMem_age(rs.getInt("MEMAGE"));
	           member.setMem_pw(rs.getString("MEMPW"));
	           member.setMem_pnum(rs.getString("MEMPNUM"));
	           member.setMem_height(rs.getString("MEMHEIGHT"));
	           member.setMem_weight(rs.getString("MEMWEIGHT"));
	            MList.add(member);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close(); // 연결 닫기
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return MList; // 회원 목록 반환
	}

	//강사 목록 조회
	public List<Teacher> TList(){
		List<Teacher> TList = new ArrayList<Teacher>();
	    Connection conn = getConnect();
	    String sql="SELECT * FROM tbl_teacher";
	    
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setT_id(rs.getString("TID"));
				teacher.setT_pw(rs.getString("TPW"));
				teacher.setT_age(rs.getInt("Age"));
				teacher.setT_name(rs.getString("TNAME"));
				teacher.setT_Pnum(rs.getString("TPNUM"));
				teacher.setHire_date(rs.getString("hire_date"));
				TList.add(teacher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        try {
	            if (conn != null) {
	                conn.close(); // 연결 닫기
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return TList; // 회원 목록 반환
        
	}

}