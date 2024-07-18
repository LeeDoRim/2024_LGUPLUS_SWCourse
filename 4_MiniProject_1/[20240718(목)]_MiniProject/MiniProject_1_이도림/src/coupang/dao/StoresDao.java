package coupang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coupang.common.DBManager;
import coupang.dto.Stores;

public class StoresDao {
	
	// store 추가
	public int insertStores(Stores Store) {
		int ret = -1;
		String sql = "insert into Stores values (?, ?, ?, ?, ?, ?, ?); ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Store.getStoreId());
			pstmt.setInt(2, Store.getCategoryId());
			pstmt.setString(3, Store.getStoreName());
			pstmt.setString(4, Store.getLocation());
			pstmt.setString(5, Store.getTell());
			pstmt.setString(6, Store.getOperatingHours());
			pstmt.setInt(7, Store.getMinPrice());
			
			ret = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}

		return ret;
	}
	
	// store 수정
	public int updateStore(Stores Store) {
		int ret = -1;
		String sql = "update stores set categoryid = ?, storename = ?, location = ?, tell = ?, operatingHours = ?, minPrice = ? where storeid = ?; ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Store.getCategoryId());
			pstmt.setString(2, Store.getStoreName());
			pstmt.setString(3, Store.getLocation());
			pstmt.setString(4, Store.getTell());
			pstmt.setString(5, Store.getOperatingHours());
			pstmt.setInt(6, Store.getMinPrice());
			pstmt.setInt(7, Store.getStoreId());
			
			ret = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}
		
		return ret;
	}
	
	// store 삭제
	public int deleteStore(int storeId) {
		int ret = -1;
		String sql = "delete from stores where storeid = ?; ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, storeId);
			
			ret = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}
		
		
		return ret;
	}

	// stores 전체 목록 확인
	public List<Stores> listStores() {
		List<Stores> list = new ArrayList<Stores>();
		String sql = "select * from stores; ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Stores store = new Stores();
				store.setStoreId(rs.getInt("storeid"));
				store.setCategoryId(rs.getInt("categoryid"));
				store.setStoreName(rs.getString("storename"));
				store.setLocation(rs.getString("location"));
				store.setTell(rs.getString("tell"));
				store.setOperatingHours(rs.getString("operatingHours"));
				store.setMinPrice(rs.getInt("minprice"));
				
				list.add(store);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return list;
	}
	
	// 스토어 1개 확인
	public Stores detailStore(int storeId) {
		Stores store = null;
		String sql = "select * from stores where storeid = ?; ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, storeId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				store = new Stores();
				store.setStoreId(rs.getInt("storeid"));
				store.setCategoryId(rs.getInt("categoryid"));
				store.setStoreName(rs.getString("storename"));
				store.setLocation(rs.getString("location"));
				store.setTell(rs.getString("tell"));
				store.setOperatingHours(rs.getString("operatingHours"));
				store.setMinPrice(rs.getInt("minprice"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, con);
		}
		
		return store;
	}
	
	
}
