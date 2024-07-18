package coupang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coupang.common.DBManager;
import coupang.dto.Menu;
import coupang.dto.Stores;

public class MenuDao {

	// Menu 추가
	public int insertMenu(Menu menu) {
		int ret = -1;
		String sql = "insert into menuinfo values (?, ?, ?, ?, ?, ?, ?); ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menu.getMenuId());
			pstmt.setInt(2, menu.getStoreId());
			pstmt.setString(3, menu.getName());
			pstmt.setString(4, menu.getDescription());
			pstmt.setInt(5, menu.getPrice());
			pstmt.setString(6, menu.getCategory());
			pstmt.setString(7, menu.getImageUrl());

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}

	// menu 수정
	public int updateMenu(Menu menu) {
		int ret = -1;
		String sql = "update menuinfo set storeid = ?, name = ?, description = ?, price = ?, category = ?, imageurl = ? where menuid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menu.getStoreId());
			pstmt.setString(2, menu.getName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setString(5, menu.getCategory());
			pstmt.setString(6, menu.getImageUrl());
			pstmt.setInt(7, menu.getMenuId());

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}

	// menu 삭제
	public int deleteMenu(int menuId) {
		int ret = -1;
		String sql = "delete from menuinfo where menuid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);

			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}

	// 하나의 store의 Menu 전체 목록 확인
	public List<Menu> listMenu(int storeId) {
		List<Menu> list = new ArrayList<Menu>();
		String sql = "select * from menuinfo where storeid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, storeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt("menuid"));
				menu.setStoreId(rs.getInt("storeid"));
				menu.setName(rs.getString("name"));
				menu.setDescription(rs.getString("description"));
				menu.setPrice(rs.getInt("price"));
				menu.setCategory(rs.getString("category"));
				menu.setImageUrl(rs.getString("imageurl"));

				list.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}

		return list;
	}

	// menu 1개 확인
	public Menu detailMenu(int menuId) {
		Menu menu = null;
		String sql = "select * from menuinfo where menuid = ?; ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, menuId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				menu = new Menu();
				menu.setMenuId(rs.getInt("menuid"));
				menu.setStoreId(rs.getInt("storeid"));
				menu.setName(rs.getString("name"));
				menu.setDescription(rs.getString("description"));
				menu.setPrice(rs.getInt("price"));
				menu.setCategory(rs.getString("category"));
				menu.setImageUrl(rs.getString("imageurl"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return menu;
	}

}
