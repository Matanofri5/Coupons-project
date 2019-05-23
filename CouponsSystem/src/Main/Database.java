package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Matan and liony
 * In this Database class we create method "getDriverData" that gives as access to apache driver.
 * also, "getDBURL" is our server, now it local on command line by port 3301.
 * in "createTable" method, we creating the table and columns to every sql table. 
 *
 */
public class Database {

	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}

	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/JBDB;create=true";
	}
	
	private static ConnectionPool pool;
	
	public Database() throws Exception{
		pool = ConnectionPool.getInstance();
	}	
	
	public static void createTables() throws SQLException {
		String sql;
		
		try {
			pool = ConnectionPool.getInstance();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		Connection connection = null;;
		try {
			connection = pool.getConnection();
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		
		// *****************Creating Company table******************
		
			sql = "create table Company ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "companyName varchar(50) not null, " + "password varchar(50) not null, "
					+ "email varchar(50) not null)";
			System.out.println("create Company table success :D  ");
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("success: " + sql);
		} catch (Exception e3) {
			System.out.println("create company didn't succeed" + e3.getMessage());
		}

		// *****************Creating Customer table******************

			sql = "create table Customer ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "customerName varchar(50) not null, " + "password varchar(50) not null)";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("success: " + sql);
			} catch (Exception e4) {
				System.out.println("create customer didn't succeed" + e4.getMessage());
			}

		// *****************Creating Coupon table*********************

			sql = "create table Coupon ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "title varchar(50) not null, " + "startDate Date not null, " + "endDate Date not null, "
					+ "amount int not null, " + "message varchar(50) not null, " + "price double not null, "
					+ "image varchar(50) not null, " + "type varchar(20) not null)";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("success: " + sql);
			} catch (Exception e4) {
				System.out.println("create coupon didn't succeed" + e4.getMessage());
			}

		// ***************Creating join CustomerCoupon table**********

			sql = "create table CustomerCoupon (" + "customerId bigint not null references Customer(id), "
					+ "couponId bigint not null references Coupon(id), " + "primary key(customerId, couponId))";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("success: " + sql);
			} catch (Exception e5) {
				System.out.println("create customercoupon didn't succeed" + e5.getMessage());
			}

		// ****************Creating join CompanyCoupon table**********

			sql = "create table CompanyCoupon (" + "companyId bigint not null references Company(id), "
					+ "couponId bigint not null references Coupon(id), " + "primary key(companyId, couponId))";
			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("success: " + sql);
			} catch (Exception e6) {
				System.out.println("create companycoupon didn't succeed" + e6.getMessage());
			}
	}
}
