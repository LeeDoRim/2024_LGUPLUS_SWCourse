package coupang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coupang.common.DBManager;
import coupang.dto.Stores;
import coupang.dto.User;

public class UserDao {

	// user 추가
	public int insertUser(User user) {
		int ret = -1;
		String sql = "insert into userinfo values (?, ?, ?, ?, ?); ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getRegisterDate());

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}

	// user 수정
	public int updateUser(User user) {
		int ret = -1;
		String sql = "update userinfo set name = ?, email = ?, phone = ? where userid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhone());
			pstmt.setInt(4, user.getUserId());

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}

	// user 삭제
	public int deleteUser(int userId) {
		int ret = -1;
		String sql = "delete from userinfo where userid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, userId);

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}
	
	// 회원 1 명 정보 확인
	public User detailUser(int userId) {
		User user = null;
		String sql = "select * from userinfo where userid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRegisterDate(rs.getString("registerdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return user;
	}

}
