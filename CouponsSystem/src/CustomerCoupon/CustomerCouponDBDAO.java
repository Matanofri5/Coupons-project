package CustomerCoupon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Customer.Customer;
import Main.ConnectionPool;
import Main.Database;

/**
 * @Author - Linoy & Matan
 * @Description: In this class we have to implement all the method in
 *               CustomerCouponDAO every method getting connection to DB and
 *               close when finished, and run an SQL Query by prepareStatement
 */
public class CustomerCouponDBDAO implements CustomerCouponDAO {

	/**
	 * Data Members
	 */
	private ConnectionPool connectionPool;
//	private Connection con;

	/**
	 * @throws Exception
	 * @Empty CTOR
	 */
	public CustomerCouponDBDAO() throws Exception {
		try {
			this.connectionPool = ConnectionPool.getInstance();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
	}

	/**
	 * @insert this method Receives data about a new CustomerCoupon, And creates it
	 *         in a table of CustomerCoupon.
	 * @param CustomerCoupon
	 *            object
	 * @throws Exception
	 */
	@Override
	public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "INSERT INTO CustomerCoupon (customerId,couponId)  VALUES(?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setLong(1, customerCoupon.getCustomerId());
			pstmt.setLong(2, customerCoupon.getCouponId());

			pstmt.executeUpdate();

			// System.out.println("CustomerCoupon insert success :D " +
			// customerCoupon.toString());
		} catch (SQLException e) {
			System.err.println("CustomerCoupon insert failed :( ");
			System.err.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
	}

	/**
	 * @remove this method delete 1 object of CustomerCoupon by Customer & Coupon
	 *         id, from CustomerCoupon table.
	 * @param long
	 *            Customer & Coupon id
	 * @throws Exception
	 */
	@Override
	public void removeCustomerCoupon(long customerId, long couponId) throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		String sql = "DELETE FROM CustomerCoupon WHERE customerId=? and couponId=?";

		try (PreparedStatement pstm1 = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			pstm1.setLong(1, customerId);
			pstm1.setLong(2, couponId);
			pstm1.executeUpdate();
			connection.commit();
			// System.out.println("remove CustomerCoupon success :D ");
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new Exception("Database error");
			}
			System.err.println("remove CustomerCoupon failed :( ");
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
	}

//	 @Override
//	 public void removeCustomerCoupon(Customer customer) throws Exception {
//	Connection connection = null;
//	 try {
//	 connection = ConnectionPool.getInstance().getConnection();
//	 } catch (Exception e) {
//	 throw new Exception("connection pool faild :(");
//	 } String sql = "DELETE FROM CustomerCoupon WHERE customerId=?";
//	
//	 try (PreparedStatement pstm1 = connection.prepareStatement(sql);) {
//	 connection.setAutoCommit(false);
//	 pstm1.setLong(1, customer.getId());
//	 pstm1.executeUpdate();
//	 connection.commit();
//	 System.out.println("remove CustomerCoupon success :D ");
//	 } catch (SQLException e) {
//	 try {
//	 connection.rollback();
//	 } catch (SQLException e1) {
//	 throw new Exception("Database error");
//	 }
//	 System.err.println("remove CustomerCoupon failed :( ");
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e2) {
//				 System.out.println(e2.getMessage());
//			}
//			try {
//				connectionPool.returnConnection(connection);
//			} catch (SQLException e3) {
//				System.out.println(e3.getMessage());
//			}
//		}
//	}

	@Override
	public void updateCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		try (Statement stm = connection.createStatement()) {
			String sql = "UPDATE CustomerCoupon " + " SET customerId='" + customerCoupon.getCustomerId()
					+ "', couponId='" + customerCoupon.getCouponId() + "' WHERE Id=" + customerCoupon.getCustomerId()
					+ "AND" + "' WHERE Id=" + customerCoupon.getCouponId();
			stm.executeUpdate(sql);
			System.out.println("updated CustomerCoupon successe :D");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new Exception("update CustomerCoupon failed :( ");
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
	}

	@Override
	public CustomerCoupon getCustomerCoupon(long id) throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		CustomerCoupon customerCoupon = new CustomerCoupon();
		try (Statement stm = connection.createStatement()) {
			String sql = "SELECT * FROM CustomerCoupon WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				customerCoupon.setCustomerId(1);
				customerCoupon.setCouponId(2);
				// System.out.println("Get CustomerCoupon success :D ");
			}

		} catch (SQLException e) {
			System.err.println("Get CustomerCoupon failed :(");
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
		return customerCoupon;
	}

	@Override
	public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		Set<CustomerCoupon> set = new HashSet<>();
		try {
			Statement stm = connection.createStatement();
			String sql = "SELECT * FROM CustomerCoupon";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {

				long customerId = rs.getLong(1);
				long couponId = rs.getLong(2);

				set.add(new CustomerCoupon(customerId, couponId));
				// System.out.println("Get all CustomerCoupon success :D ");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.err.println("Get all CustomerCoupon failed :( ");
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
		return set;
	}

	@Override
	public Collection<Long> getAllCouponsId(long customerId) throws Exception {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("connection pool faild :(");
		}
		Collection<Long> couponsId = new ArrayList<>();
		String sql = "SELECT * from CustomerCoupon WHERE customerId = " + customerId;
		try (Statement stm = connection.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			while (rs.next()) {
				long couponId = rs.getLong(2);
				couponsId.add(couponId);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				 System.out.println(e2.getMessage());
			}
			try {
				connectionPool.returnConnection(connection);
			} catch (SQLException e3) {
				System.out.println(e3.getMessage());
			}
		}
		return couponsId;
	}
}
