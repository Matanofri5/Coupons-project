package Coupon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import Main.Database;

public class CouponDBDAO implements CouponDAO {
	Connection con;

	@Override
	public void insertCoupon(Coupon coupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "INSERT INTO Coupon (title,startDate,endDate,amount,message,price,image,type)  VALUES(?,?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getMessage());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setString(8, coupon.getType().name());
			pstmt.executeUpdate();

			System.out.println("Coupon insert success :D  " + coupon.toString());
		} catch (SQLException e) {
			System.err.println("Coupon insert failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void removeCoupon(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "DELETE FROM Coupon WHERE id=?";
		try (PreparedStatement pstm1 = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstm1.setLong(1, id);
			pstm1.executeUpdate();
			con.commit();
			System.out.println("remove Coupon success :D ");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("remove Coupon failed :( ");
				throw new Exception("Database error");
			}
		} finally {
			con.close();
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "UPDATE Coupon " + " SET endDate='" + "', price=" + coupon.getPrice() + "' WHERE id=" + coupon.getId();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1,(Date) coupon.getEndDate());
			pstmt.setDouble(2, coupon.getPrice());
			
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("updated Coupon successe :D" + coupon.getId());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update Coupon failed :( ");
		}
	}

	@Override
	public Coupon getCoupon(long id) throws Exception {
		Coupon coupon = new Coupon();
		con = DriverManager.getConnection(Database.getDBUrl());
		java.sql.Statement stm = null;

		try {
			stm = con.createStatement();
			String sql = "SELECT * FROM Coupon WHERE id=" + id;
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			coupon.setId(rs.getLong(1));
			coupon.setTitle(rs.getString(2));
			coupon.setStartDate((Date) rs.getDate(3));
			coupon.setEndDate((Date) rs.getDate(4));
			coupon.setAmount(rs.getInt(5));
			coupon.setImage(rs.getString(6));
			coupon.setPrice(rs.getDouble(7));
			coupon.setMessage(rs.getString(8));
			CouponType type = CouponType.valueOf(rs.getString(9));
			coupon.setType(type);
			System.out.println("Get coupon success :D ");

		} catch (SQLException e) {
			System.err.println("Get coupon failed :(");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return coupon;
	}

	@Override
	public Set<Coupon> getAllCoupons() throws Exception {
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
				coupon.setEndDate((Date) rs.getDate(3));
				coupon.setStartDate((Date) rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setMessage(rs.getString(6));
				coupon.setPrice(rs.getDouble(7));
				coupon.setImage(rs.getString(8));
				CouponType type = CouponType.valueOf(rs.getString(9));
				coupon.setType(type);
				coupons.add(coupon);
				System.out.println("Get all coupon success :D ");
			}
		} catch (SQLException e) {
			System.err.println("Get all coupon failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return coupons;
	}


	@Override
	public void dropTable() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "DROP TABLE Coupon";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("drop Coupon Table success!! :D ");

		} catch (SQLException ex) {
			System.err.println("MMMMMMM....dropCouponTableEXCEPTION");
			throw new Exception(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println("the connection cannot closed :( " + ex.getMessage());
			}
		}

	}
}
