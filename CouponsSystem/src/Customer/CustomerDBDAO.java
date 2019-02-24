package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import Main.Database;

public class CustomerDBDAO implements CustomerDAO {
	Connection con;

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

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try (Statement stm = con.createStatement()) {
			String sql = "UPDATE Customer " + " SET customerName='" + customer.getCustomerName() + "', password='"
					+ customer.getPassword() + "' WHERE ID=" + customer.getId();

			stm.executeUpdate(sql);
			System.out.println("updated customer successe :D" + customer.getId());

		} catch (SQLException e) {
			System.err.println("update Customer failed :( ");
			throw new Exception(e.getMessage());
		}
	}

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
				System.out.println("Get customer success :D ");
			}
		} catch (SQLException e) {
			System.err.println("Get customer failed :(");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return customer;
	}

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
				System.out.println("Get all customer success :D ");
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