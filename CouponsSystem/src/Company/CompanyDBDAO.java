package Company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.LongToDoubleFunction;

import CompanyCoupon.CompanyCoupon;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Main.Database;
import MyExceptions.LoginException;
import MyExceptions.RemoveCouponException;

/**
 * @Author - Linoy & Matan
 * @Description: In this class we have to implement all the method in CompanyDao
 * every method getting connection to DB and close when finished, and run an SQL
 * Query by prepareStatement
 */

public class CompanyDBDAO implements CompanyDAO {
	
	/**
	 * Data Members
	 */
	Connection con;
	

	/**
	 * @Empty CTOR
	 */
	public CompanyDBDAO() {
	}
	
	/**
	 * @insert
	 * this method Receives data about a new company, And creates it in a table of companies.
	 *  @param company object
	 *  @throws Exception
	 */
	@Override
	public void insertCompany(Company company) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		String sql = "INSERT INTO Company (companyName,password,email)  VALUES(?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, company.getCompanyName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());

			pstmt.executeUpdate();
			System.out.println("Company insert success :D  " + company.toString());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.err.println("Company insert failed :(");
		} finally {
			con.close();
		}
	}

	/**
	 * @remove
	 * this method delete 1 object of company by company id, from companies table.
	 *  @param long id
	 *  @throws Exception
	 */
	@Override
	public void removeCompany(Company company) throws Exception {		
		con = DriverManager.getConnection(Database.getDBUrl());

		try {
			String sql = "DELETE FROM Company WHERE id= ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, company.getId());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Company " + company.getId() + " remove success :D ");
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				System.out.println(e1.getMessage());
				throw new Exception("Database error");
			}
			e.printStackTrace();
			System.err.println("Company remove failed :( ");
		} finally {
			con.close();
		}
	}
	
	/**
	 * @removeCouponFromCompany
	 * this method delete 1 coupon of Company by DELETE query from three tables.
	 *  @param long couponId
	 *  @param long  id
	 *  @throws Exception
	 */
	@Override
	public void removeCouponFromCompany(long couponId, long id) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());

		try {
			
		//query to delete coupon from companyCoupon table
			String sql1 = "DELETE FROM CompanyCoupon WHERE couponId=?";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setLong(1, couponId);
			pstmt.executeUpdate();
			pstmt.close();
			
			//query to delete coupon from customerCoupon table
				String sql2 = "DELETE FROM CustomerCoupon WHERE couponId=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setLong(1, couponId);
				pstmt2.executeUpdate();
				pstmt2.close();
				
				//query to delete coupon from coupon table
					String sql3 = "DELETE FROM Coupon WHERE id=?";
					PreparedStatement pstmt3 = con.prepareStatement(sql3);
					pstmt3.setLong(1, id);
					pstmt3.executeUpdate();
					pstmt3.close();
					
					System.out.println("you deleted coupon from company successfully");
		}
		catch (Exception e) {
			throw new RemoveCouponException("failed to remove coupon from company");
		}
		finally {
			con.close();
		}
	}

	/**
	 * @update
	 * this method update 1 object of company, from companies table.
	 *  @param company object
	 *  @throws Exception
	 */
	@Override
	public void updateCompany(Company company) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "UPDATE Company SET password=?, email=? WHERE companyName=?";
			PreparedStatement pstm = con.prepareStatement(sql);
			
			pstm.setString(1, company.getPassword());
			pstm.setString(2, company.getEmail());
			pstm.setString(3, company.getCompanyName());
			pstm.executeUpdate();
			pstm.close();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new Exception("Company update failed :( ");
		}
	}

	/**
	 * @get1
	 * this method get and print 1 object of company by company id, from companies table.
	 *  @param long id
	 *  @return company object
	 *  @throws Exception
	 */
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
			System.err.println("Get company failed :(");
		} finally {
			con.close();

		}
		return company;
	}

	/**
	 * @getAll
	 * this method get all and print objects of company, from companies table.
	 *  @return company object
	 *  @throws Exception
	 */
	@Override
	public Set<Company> getAllCompanys() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<Company> set = new HashSet<Company>();
		String sql = "SELECT * FROM Company";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				long id = rs.getLong(1);
				String companyName = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				set.add(new Company(id, companyName, password, email));
//				System.out.println("Get all company success :D ");
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new Exception("Get all company failed :( ");
		} finally {
			con.close();
		}
		return set;
	}

	/**
	 * @getAll coupons by company id
	 * this method get and print objects of coupons by company id, from CompanyCoupon table.
	 *  @param long id
	 *  @return coupon object
	 *  @throws Exception
	 */
	@Override
	public Set<Coupon> getAllCompanyCoupons(long companyId) throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		Set<Coupon> coupons = new HashSet<Coupon>();
		CouponDBDAO coupon = new CouponDBDAO();

		try {
			String sql = "SELECT COUPONID FROM CompanyCoupon WHERE COMPANYID=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,companyId) ;
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				coupons.add(coupon.getCoupon(rs.getLong("COUPONID")));

				System.out.println("Get coupons by company success :D ");
			}
		} catch (SQLException e) {
			System.err.println("Get coupons by company failed :( ");
			throw new Exception(e.getMessage());
		} finally {
			con.close();
		}
		return coupons;
	}
	
	/**
	 * this method login by companyUser check companyName and password
	 * @throws Exception
	 * @return boolean
	 */
	@Override
	public boolean login(String companyName, String password) throws Exception, LoginException {
		con = DriverManager.getConnection(Database.getDBUrl());
		
		boolean logicSuccess = false;
		
		try {
			String sql = "SELECT * FROM company WHERE COMPANYNAME=? AND PASSWORD=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, companyName);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				logicSuccess = true;
			}
			
		}catch (Exception e) {
			throw new LoginException("comapny failed to login");
		}finally {
			con.close();
		}
		return logicSuccess;
	}
	
	/**
	 * @dropTable
	 * this method delete all the table of companies.
	 *  @throws Exception
	 */
	@Override
	public void dropTable() throws Exception {
		con = DriverManager.getConnection(Database.getDBUrl());
		try {
			String sql = "DROP TABLE Company";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("drop Company Table success!! :D ");

		} catch (SQLException ex) {
			System.err.println("MMMMMMM....dropCompanyTableEXCEPTION :( ");
			throw new Exception(ex.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println("the connection cannot closed :( " + ex.getMessage());
			}
		}
	}

	/**
	 * @addCoupon
	 * this method insert new coupon And check if the title already exists.
	 *  @param Coupon object
	 *  @throws Exception
	 */
//	@Override
//	public void addCoupon(Coupon coupon) throws Exception {
//		con = DriverManager.getConnection(Database.getDBUrl());
//		try {
//
//			Set<Coupon> coupons = couponDAO.getAllCoupons();
//			Iterator<Coupon> i = coupons.iterator();
//			while (i.hasNext()) {
//				Coupon current = i.next();
//				if (coupon.getTitle().equals(current.getTitle())) {
//					throw new Exception("this coupon already exists");	
//				}
//			}
//			if (!i.hasNext()) {
//				couponDAO.insertCoupon(coupon);
//				System.out.println("company added new coupon: " + coupon.getId());
//			} 
//	}
//			catch (Exception e) {
//				e.printStackTrace();
//				System.out.println(e.getMessage());
//			}finally {
//				con.close();
//			}
//	}
}
