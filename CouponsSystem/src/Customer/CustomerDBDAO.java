package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Coupon.Coupon;
import CustomerCoupon.CustomerCoupon;
import Main.Database;
import MyExceptions.LoginException;

/**
 * @Author - Linoy & Matan
 * @Description: In this class we have to implement all the method in CustomerDAO
 * every method getting connection to DB and close when finished, and run an SQL
 * Query by prepareStatement
 */
public class CustomerDBDAO implements CustomerDAO {
	
	/**
	 * Data Members
	 */
	Connection con;

	/**
	 * @Empty CTOR
	 */
	public CustomerDBDAO() {
	}
	
	/**
	 * @insert
	 * this method Receives data about a new Customer, And creates it in a table of Customers.
	 *  @param Customer object
	 *  @throws Exception
	 */
	@Override
	public void insertCustomer(Customer customer) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "INSERT INTO Customer (customerName,password)  VALUES(?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getPassword());

			pstmt.executeUpdate();
			System.out.println("Customer insert success :D  " + customer.toString());
		} catch (SQLException e) {
			System.err.println("Customer insert failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
	}

	/**
	 * @remove
	 * this method delete 1 object of Customer by Customer id, from Customers table.
	 *  @param long id
	 *  @throws Exception
	 */
	@Override
	public void removeCustomer(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "DELETE FROM Customer WHERE id= ?";

		try (PreparedStatement pstm1 = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			pstm1.setLong(1, id);
			pstm1.executeUpdate();
			con.commit();
			System.out.println("remove customer success :D ");

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.err.println("remove customer failed :( ");
				throw new Exception("Database error");
			}
		} finally {
			con.close();
		}
	}

	/**
	 * @update
	 * this method update 1 object of Customer, from Customers table.
	 *  @param Customer object
	 *  @throws Exception
	 */
	@Override
	public void updateCustomer(Customer customer) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "UPDATE Customer SET password=? WHERE customerName=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, customer.getPassword());
			pstm.setString(2, customer.getCustomerName());
			pstm.executeUpdate();
			pstm.close();
			System.out.println("updated customer id " + customer.getId() + " successfully");

		} catch (SQLException e) {
			System.err.println("update Customer failed :( ");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * @get1
	 * this method get and print 1 object of Customer by Customer id, from Customers table.
	 *  @param long id
	 *  @return Customer object
	 *  @throws Exception
	 */
	@Override
	public Customer getCustomer(long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Customer customer = new Customer();
		try (Statement stm = con.createStatement()) {
			String sql = "SELECT * FROM Customer WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				customer.setId(rs.getLong(1));
				customer.setCustomerName(rs.getString(2));
				customer.setPassword(rs.getString(3));
//				System.out.println("Get customer success :D ");
			}
		} catch (SQLException e) {
			System.err.println("Get customer failed :(");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return customer;
	}

	/**
	 * @getAll
	 * this method get all and print objects of Customers, from Customer table.
	 *  @return Customer list object
	 *  @throws Exception
	 */
	@Override
	public Set<Customer> getAllCustomer() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<Customer> set = new HashSet<>();
		String sql = "SELECT * FROM Customer";
		try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
			while (rs.next()) {
				long id = rs.getLong(1);
				String customerName = rs.getString(2);
				String password = rs.getString(3);
				set.add(new Customer(id, customerName, password));
			}
		} catch (SQLException e) {
			System.err.println("Get all customer failed :( ");
			System.err.println(e.getMessage());
		} finally {
			con.close();
		}
		return set;
	}
	
	@Override
	public void customerPurchaseCoupon (Coupon coupon, Customer customer) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try  {
			String sql = "INSERT INTO CustomerCoupon (customerId,couponId)  VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());

			pstmt.executeUpdate();

			System.out.println("Customer purchase coupon :D  ");
		} catch (SQLException e) {
			System.err.println("customer failed to purchase coupon :( ");
			System.err.println(e.getMessage());
		} finally {
			con.close();
		}
	}
	
	@Override
	public Set<Coupon> getAllCustomerCoupons(long customerId) throws Exception {
		return null;
	}
	
	@Override
	public boolean login(String customerName, String password) throws Exception, LoginException {
		con = DriverManager.getConnection(Database.getDBUrl());
		
		boolean logicSuccess = false;
		
		try {
			String sql = "SELECT * FROM customer WHERE CUSTOMERNAME=? AND PASSWORD=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customerName);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				logicSuccess = true;
			}
			
		}catch (Exception e) {
			throw new LoginException("customer failed to login");
		}finally {
			con.close();
		}
		return logicSuccess;
	}

	@Override
	public void dropTable() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "DROP TABLE Customer";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("drop Customer Table success!! :D ");

		} catch (SQLException ex) {
			System.err.println("MMMMMMM....dropCustomerTableEXCEPTION");
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