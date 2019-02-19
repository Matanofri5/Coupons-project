package Coupon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import Main.*;
import Main.Database;

	public class CouponDBDAO implements CouponDAO {
		Connection con;

		@Override
		public void insertCoupon(Coupon coupon) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String sql = "INSERT INTO Coupon (Title,StartDate,EndDate,Amount,Message,Price,Image,Type)  VALUES(?,?,?,?,?,?,?,?)";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, coupon.getTitle());
				pstmt.setDate(2, (Date) coupon.getStartDate());
                pstmt.setDate(3, (Date) coupon.getEndDate());
				pstmt.setInt(4, coupon.getAmount());
				pstmt.setString(5, coupon.getMessage());
                pstmt.setDouble(6,  coupon.getPrice());
				pstmt.setString(7, coupon.getImage());
                pstmt.setString(8, coupon.getType().name());
				pstmt.executeUpdate();
				
				System.out.println("Coupon insert" +" " + coupon.toString());
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Coupon insert failed");
			} finally {
				con.close();
			}
		}

		@Override
		public void removeCoupon(long id) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String pre1 = "DELETE FROM Coupon WHERE id=?";

			try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
				con.setAutoCommit(false);
				pstm1.setLong(1, id);
				pstm1.executeUpdate();
				con.commit();
				System.out.println("Coupon Id " + id + " removed successfully  :) !!!");

			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new Exception("Database error");
				}
				throw new Exception("failed to remove coupon");
			} finally {
				con.close();
			}
		}

		@Override
		public void updateCoupon(Coupon coupon) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			java.sql.Statement stm = null;
			try {
			stm = con.createStatement();
			
			String sql = "UPDATE Coupon "
			+ " SET Title='" + coupon.getTitle()
			+ "', StartDate='" + (Date) coupon.getStartDate()
			+ "', EndDate='" + (Date) coupon.getEndDate()
			+ "', Amount=" + coupon.getAmount()
			+ ", Message='" + coupon.getMessage()
			+ "', Price=" + coupon.getPrice()
			+ ", Image='" + coupon.getImage()
			+ "', Type='" + coupon.getType()
			+ "' WHERE ID=" + coupon.getId();
			
				stm.executeUpdate(sql);
				System.out.println("Coupon updated successefully");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("update Coupon failed");
			}
		}

		@Override
		public Coupon getCoupon(long id) throws Exception {
			Coupon coupon = new Coupon();
			con = DriverManager.getConnection(Database.getDBUrl());
			java.sql.Statement stm = null;
			
			try {
				stm = con.createStatement(); 
				String sql = "SELECT * FROM Coupon WHERE ID=" + id;
				ResultSet rs = stm.executeQuery(sql);
			    rs.next();
				coupon.setId(rs.getLong(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStartDate((Date)rs.getDate(3));
				coupon.setEndDate((Date)rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setImage(rs.getString(6));
				coupon.setPrice(rs.getDouble(7));
				coupon.setMessage(rs.getString(8));
				CouponType type = CouponType.valueOf(rs.getString(9));
				coupon.setType(type);
			
			} catch (SQLException e) {
			System.out.println(e.getMessage());
				throw new Exception("unable to get coupon data");
			} finally {
					con.close();
			}
				return coupon;
		}
		
		@Override
		public Set<Coupon> getAllCoupon() throws Exception {
			Coupon coupon;
			Set<Coupon> coupons = new HashSet<Coupon>();
			con = DriverManager.getConnection(Database.getDBUrl());
            java.sql.Statement stm = null;
			try { 
				stm = con.createStatement();
				String sql = "SELECT * FROM Coupon";
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					coupon = new Coupon();
					coupon.setId(rs.getLong(1));
					coupon.setTitle(rs.getString(2));
					coupon.setEndDate((Date)rs.getDate(3));
					coupon.setStartDate((Date)rs.getDate(4));
					coupon.setAmount(rs.getInt(5));
					coupon.setMessage(rs.getString(6));
					coupon.setPrice(rs.getDouble(7));
					coupon.setImage(rs.getString(8));
					CouponType type = CouponType.valueOf(rs.getString(9));
					coupon.setType(type);
					coupons.add(coupon);
			}
			} catch (SQLException e) {
				throw new Exception("cannot get Coupon data");
			} finally {
				con.close();
			}
			return coupons;
		}

		@Override
		public void dropTable() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try {
				String sql =  "DROP TABLE Coupon";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("drop Table success!! :D ");

			}
			catch (SQLException ex) {
				System.err.println("MMMMMMM....dropTableEXCEPTION");
				throw new Exception(ex.getMessage());
			}
			finally {
				try {
					con.close();
				} catch (SQLException ex) {
					System.err.println("the connection cannot closed :( "+ ex.getMessage());
				}
			}
			
		
		}
	}

