package Company;

    import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.HashSet;
	import java.util.Set;
	import Main.Database;

	public class CompanyDBDAO implements CompanyDAO {
		Connection con;

		@Override
		public void insertCompany(Company company) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String sql = "INSERT INTO Company (companyName,password,email)  VALUES(?,?,?)";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {

				pstmt.setString(1, company.getCompanyName());
				pstmt.setString(2, company.getPassword());
                pstmt.setString(3, company.getEmail());
                
				pstmt.executeUpdate();
				System.out.println("Company " + company.getCompanyName() + " successefully inserted :) !!!");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Company insert failed");
			} finally {
				con.close();
			}
		}

		@Override
		public void removeCompany(long id) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			String pre1 = "DELETE FROM Company WHERE id= ?";

			try (PreparedStatement pstm1 = con.prepareStatement(pre1);) {
				con.setAutoCommit(false);
				pstm1.setLong(1, id);
				pstm1.executeUpdate();
				con.commit();
				System.out.println("Company id " + id + " removed seccessfully  :) !!!");
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					throw new Exception("Database error");
				}
				throw new Exception("failed to remove company");
			} finally {
				con.close();
			}
		}

		@Override
		public void updateCompany(Company company) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try (Statement stm = con.createStatement()) {
			String sql = "UPDATE Company "
			+ " SET companyName='" +company.getCompanyName()
			+ "', password='" + company.getPassword()
			+"', email='" + company.getEmail()
			+ "' WHERE id=" + company.getId();
				
			System.out.println("Company id " + company.getId() + " updated successfully !!! :)");
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("update Company failed");
			}
		}

		@Override
		public Company getCompany(long id) throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			Company company = new Company();
			try (Statement stm = con.createStatement()) {
				String sql = "SELECT * FROM Company WHERE ID=" + id;
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
				company.setId(rs.getLong(1));
				company.setCompanyName(rs.getString(2));
				company.setPassword(rs.getString(3));
                company.setEmail(rs.getString(4));
				}
            
			} catch (SQLException e) {
				throw new Exception("unable to get company data");
			} finally {	
				con.close();	
				
			}
			return company;
		}

		@Override
		public Set<Company> getAllCompany() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			Set<Company> set = new HashSet<>();		
			try {
				Statement stm = con.createStatement();
				String sql = "SELECT * FROM Company";
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					long id = rs.getLong(1);
					String companyName = rs.getString(2);
					String password = rs.getString(3);
                    String email = rs.getString(4);
                    
					set.add(new Company(id, companyName, password, email));
				}
			} catch (SQLException e) {
				System.out.println(e);
				throw new Exception("cannot get Company data");
			} finally {
				con.close();
			}
			return set;
		}

		@Override
		public void dropTable() throws Exception {
			con = DriverManager.getConnection(Database.getDBUrl());
			try {
				String sql =  "DROP TABLE Company";
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

