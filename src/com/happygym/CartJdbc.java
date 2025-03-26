package com.happygym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartJdbc {
	// 커넥션 객체 생성 .
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

	// 장바구니 목록
	public List<Cart> getCart(String memID){
		List<Cart> list =new ArrayList<Cart>();
		Connection conn= getConnect();
		String sql="SELECT * FROM cart where MEMBER_ID=?";
		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, memID);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setCartid(rs.getString("CART_ID"));
				cart.setMemId(rs.getString("MEMBER_ID"));
				cart.setCourseId(rs.getInt("COURSE_ID"));
				cart.setAddeddate(rs.getTimestamp("ADDED_DATE"));
				list.add(cart);
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
	// 장바구니 추가
	public boolean addCart(Cart cart) {
		Connection conn = getConnect();
		String sql = "INSERT INTO cart(CART_ID,MEMBER_ID,COURSE_ID,ADDED_DATE)"
				+ "SELECT ?,?,?,? FROM cart where COURSE_ID=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, cart.getCartid());
			psmt.setString(2, cart.getMemId());
			psmt.setInt(3, cart.getCourseId());
			psmt.setTimestamp(4, cart.getAddeddate());
			psmt.setInt(5, cart.getCourseId());
			int r = psmt.executeUpdate();
			return r > 0;
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
	//장바구나 삭제 쿼리
	public boolean deleteCart(Cart cart) {
		Connection conn = getConnect();
		String sql="delete from cart where course_id=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, cart.getCourseId());
			int r=psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//코스 아이디로 
	public Course getCourseById(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

}
//장바구니 삭제