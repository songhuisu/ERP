package com.java.data;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventDAO {

	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String uid = "song";
	String upw = "adminsong";

	// DB저장
	public void connect() {
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, uid, upw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDate() {

		String sql = "SELECT SYSDATE FROM DUAL"; // 현재 시간

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next())
				return rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	// 로그인 
	public int login(int eNum, String ePass) {
		connect();

		String query = "select epass from inmember where enum = ?";

		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, eNum);

			rs = pst.executeQuery();

			while (rs.next()) {

				if (rs.getString("epass").equals(ePass)) {
					return 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public ArrayList<InventDTO> click(){
		
		connect();
		
		String query = "select * from inven where iOffName = ? OR iCode = ?";
		
		try {
			ArrayList<InventDTO> invent = new ArrayList<InventDTO>();
			
			pst = con.prepareStatement(query);			
			rs = pst.executeQuery();

			while (rs.next()) {
				InventDTO dto = new InventDTO();

				dto.setiOffName(rs.getString("iOffName"));
				dto.setiCode(rs.getString("iCode"));
				dto.setiName(rs.getString("iName"));
				dto.setiDate(rs.getString("iDate"));
				dto.setiAmount(rs.getInt("iAmount"));
				dto.setiPrice(rs.getInt("iPrice"));

				invent.add(dto);

			}
			
			for(InventDTO dto : invent) {
				System.out.println(dto.toString());
			}
			return invent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<InventDTO> select() {
		connect();

		String query = "select * from inven";

		try {
			ArrayList<InventDTO> invent = new ArrayList<InventDTO>();
			
			pst = con.prepareStatement(query);			
			rs = pst.executeQuery();

			while (rs.next()) {
				InventDTO dto = new InventDTO();

				dto.setiOffName(rs.getString("iOffName"));
				dto.setiCode(rs.getString("iCode"));
				dto.setiName(rs.getString("iName"));
				dto.setiDate(rs.getString("iDate"));
				dto.setiAmount(rs.getInt("iAmount"));
				dto.setiPrice(rs.getInt("iPrice"));

				invent.add(dto);

			}
			
			for(InventDTO dto : invent) {
				System.out.println(dto.toString());
			}
			return invent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public InventDTO row_select (int icode) {
		
		connect();
		
		InventDTO dto = new InventDTO();
		
		String query = "SELECT * FROM INVEN WHERE ICODE = ? ";
		
		try {
			pst = con.prepareStatement(query);
			
			pst.setInt(1,icode);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				dto.setiOffName(rs.getString("iOffName"));
				dto.setiCode(rs.getString("iCode"));
				dto.setiName(rs.getString("iName"));
				dto.setiDate(getDate());
				dto.setiAmount(rs.getInt("iAmount"));
				dto.setiPrice(rs.getInt("iprice"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public int insert(Object iOffName, String iCode, String iName,int iAmount, int iPrice) {

		connect();

		try {
			String query = "insert into inven(iOffName,iCode,iName,iDate,iAmount,iPrice) values(?,?,?,?,?,?)";

			pst = con.prepareStatement(query);

			pst.setObject(1, iOffName.toString());
			pst.setString(2, iCode);
			pst.setString(3, iName);
			pst.setString(4, getDate());
			pst.setInt(5, iAmount);
			pst.setInt(6, iPrice);
			pst.executeUpdate();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


	public int update(String iOffName, String iCode, String iName, String iDate, int iAmount, int iPrice) {

		connect();

		String query = "update inven set iAmount = ? , iPrice = ? where iOffname = ? AND icode = ?";

		try {
			pst = con.prepareStatement(query);
			pst.setInt(1,iAmount);
			pst.setInt(2,iPrice);
			pst.setString(3,iOffName);
			pst.setString(4, iCode);
			
			pst.executeUpdate();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


	public int delete(String iCode) {

		connect();

		String query = "delete from inven where iCode = ? ";

		try {
			pst = con.prepareStatement(query);

			pst.setString(1, iCode);
			
			pst.executeUpdate();

			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}