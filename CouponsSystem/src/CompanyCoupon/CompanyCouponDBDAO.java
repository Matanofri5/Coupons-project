package CompanyCoupon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import Main.Database;

import CustomerCoupon.CustomerCoupon;
import Main.Database;

public class CompanyCouponDBDAO implements CompanyCouponDAO {
	Connection con;

	@Override
	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "INSERT INTO CompanyCoupon (companyId,couponId)  VALUES(?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setLong(1, companyCoupon.getCompanyId());
			pstmt.setLong(2, companyCoupon.getCouponId());
			pstmt.executeUpdate();

			System.out.println("CompanyCoupon insert success :D  " + companyCoupon.toString());
		} catch (SQLException e) {
			System.err.println("CompanyCoupon insert failed :( ");
			System.err.println(e.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void removeCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "DELETE FROM CompanyCoupon WHERE companyId=? and couponId=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstmt.setLong(1, companyCoupon.getCompanyId());
			pstmt.setLong(2, companyCoupon.getCouponId());
			pstmt.executeUpdate();
			con.commit();
			System.out.println("remove CompanyCoupon success :D ");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new Exception("Database error");
			}
			System.err.println("remove CompanyCoupon failed :( ");
		} finally {
			con.close();
		}
	}

	@Override
	public void updateCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try (Statement stm = con.createStatement()) {
			String sql = "UPDATE CompanyCoupon " + " SET companyId='" + companyCoupon.getCompanyId() + "', couponId='"
					+ companyCoupon.getCouponId() + "' WHERE Id=" + companyCoupon.getCompanyId() + "AND" + "' WHERE Id="
					+ companyCoupon.getCouponId();
			stm.executeUpdate(sql);
			System.out.println("updated CompanyCoupon successe :D");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update CompanyCoupon failed :( ");
		}
	}

	@Override
	public CompanyCoupon getCompanyCoupon(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		CompanyCoupon companyCoupon = new CompanyCoupon();
		try (Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CompanyCoupon WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				companyCoupon.setCompanyId(1);
				companyCoupon.setCouponId(2);
				System.out.println("Get CompanyCoupon success :D ");
			}

		} catch (SQLException e) {
			System.err.println("Get CompanyCoupon failed :(");
		} finally {
			con.close();

		}
		return companyCoupon;
	}

	@Override
	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<CompanyCoupon> set = new HashSet<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM CompanyCoupon";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				long companyId = rs.getLong(1);
				long couponId = rs.getLong(2);

				set.add(new CompanyCoupon(companyId, couponId));
				System.out.println("Get all CompanyCoupon success :D ");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.err.println("Get all CompanyCoupon failed :( ");
		} finally {
			con.close();
		}
		return set;
	}

	@Override
	public void dropTable() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "DROP TABLE CompanyCoupon";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("drop Table CompanyCoupon success!! :D ");

		} catch (SQLException ex) {
			System.err.println("MMMMMMM....dropCompanyCouponTableEXCEPTION");
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
