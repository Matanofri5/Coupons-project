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
				System.out.println("Customer " + customer.getCustomerName() + " successfully inserted !!! :)");
			} catch (SQLException e) {
				throw new Exception("Customer insert failed");
			} finally {
				con.close();
			}
		}

		@Override
		public void removeCustomer(long id) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String pre1 = "DELETE FROM Customer WHERE id= ?";

			try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
				con.setAutoCommit(false);
				pstm1.setLong(1, id);
				pstm1.executeUpdate();
				con.commit();
				System.out.println("Customer Id " + id + " removed seccessfully  :) !!!");

			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new Exception("Database error");
				}
				throw new Exception("failed to remove customer");
			} finally {
				con.close();
			}
		}

		@Override
		public void updateCustomer(Customer customer) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try (Statement stm = con.createStatement()) {
				String sql = "UPDATE Customer "
			+ " SET customerName='" + customer.getCustomerName() +
			"', password='" + customer.getPassword()
			+ "' WHERE ID=" + customer.getId();
				
				System.out.println("Customer id " + customer.getId() + " updated successfully !!! :)");
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				throw new Exception("update Customer failed");
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
				} 
			} catch (SQLException e) {
				throw new Exception("unable to get customer data");
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
				}
			} catch (SQLException e) {
				System.out.println(e);
				throw new Exception("cannot get Customer data");
			} finally {
				con.close();
			}
			return set;
		}

		@Override
		public void dropTable() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try {
				String sql =  "DROP TABLE Customer";
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

