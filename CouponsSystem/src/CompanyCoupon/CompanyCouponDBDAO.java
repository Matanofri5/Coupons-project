package CompanyCoupon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Main.ConnectionPool;
import Main.Database;

import CustomerCoupon.CustomerCoupon;
import Main.Database;

/**
 * @Author - Linoy & Matan
 * @Description: In this class we have to implement all the method in
 *               CompanyCouponDAO every method getting connection to DB and
 *               close when finished, and run an SQL Query by prepareStatement
 */
public class CompanyCouponDBDAO implements CompanyCouponDAO {

	/**
	 * Data Members
	 */
	private ConnectionPool connectionPool;
	private Connection con;

	/**
	 * @throws Exception
	 * @Empty CTOR
	 */
	public CompanyCouponDBDAO() throws Exception {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
	}

	/**
	 * @insert this method Receives data about a new CompanyCoupon, And creates it
	 *         in a table of CompanyCoupon.
	 * @param CompanyCoupon
	 *            object
	 * @throws Exception
	 */
	@Override
	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "INSERT INTO CompanyCoupon (companyId,couponId)  VALUES(?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setLong(1, companyCoupon.getCompanyId());
			pstmt.setLong(2, companyCoupon.getCouponId());
			pstmt.executeUpdate();

			// System.out.println("CompanyCoupon insert success :D " +
			// companyCoupon.toString());
		} catch (SQLException e) {
			System.err.println("CompanyCoupon insert failed :( ");
			System.err.println(e.getMessage());
		} finally {
			con.close();
		}
	}

	/**
	 * @remove this method delete 1 object of CompanyCoupon by Company & Coupon id,
	 *         from CompanyCoupon table.
	 * @param long
	 *            Company & Coupon id
	 * @throws Exception
	 */
	@Override
	public void removeCompanyCoupon(long companyId, long couponId) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "DELETE FROM CompanyCoupon WHERE companyId=? and couponId=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstmt.setLong(1, companyId);
			pstmt.setLong(2, couponId);
			pstmt.executeUpdate();
			con.commit();
			// System.out.println("remove CompanyCoupon success :D ");
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
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		try (Statement stm = con.createStatement()) {
			String sql = "UPDATE CompanyCoupon " + " SET companyId='" + companyCoupon.getCompanyId() + "', couponId='"
					+ companyCoupon.getCouponId() + "' WHERE Id=" + companyCoupon.getCompanyId() + "AND" + "' WHERE Id="
					+ companyCoupon.getCouponId();
			stm.executeUpdate(sql);
			// System.out.println("updated CompanyCoupon successe :D");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update CompanyCoupon failed :( ");
		}
	}

	@Override
	public CompanyCoupon getCompanyCoupon(long id) throws Exception {
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		CompanyCoupon companyCoupon = new CompanyCoupon();
		try (Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CompanyCoupon WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				companyCoupon.setCompanyId(1);
				companyCoupon.setCouponId(2);
				// System.out.println("Get CompanyCoupon success :D ");
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
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		Set<CompanyCoupon> set = new HashSet<>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM CompanyCoupon";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				long companyId = rs.getLong(1);
				long couponId = rs.getLong(2);

				set.add(new CompanyCoupon(companyId, couponId));
				// System.out.println("Get all CompanyCoupon success :D ");
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
		try {
			con = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		try {
			String sql = "DROP TABLE CompanyCoupon";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			// System.out.println("drop Table CompanyCoupon success!! :D ");

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
