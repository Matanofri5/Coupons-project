package Main;

import java.sql.Connection;
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

	public static void createTables(Connection con) throws SQLException {
		String sql;
		
		// *****************Creating Company table******************
		
		try {
			Statement stmt = con.createStatement();

			sql = "create table Company ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "companyName varchar(50) not null, " + "password varchar(50) not null, "
					+ "email varchar(50) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create Company table success :D  ");
		} catch (SQLException e) {
		}

		// *****************Creating Customer table******************

		try {
			Statement stmt = con.createStatement();

			sql = "create table Customer ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "customerName varchar(50) not null, " + "password varchar(50) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create Customer table success :D  ");
		} catch (SQLException e) {
		}

		// *****************Creating Coupon table*********************

		try {
			Statement stmt = con.createStatement();

			sql = "create table Coupon ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "title varchar(50) not null, " + "startDate Date not null, " + "endDate Date not null, "
					+ "amount int not null, " + "message varchar(50) not null, " + "price double not null, "
					+ "image varchar(50) not null, " + "type varchar(20) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create Coupon table success :D  ");
		} catch (SQLException e) {
		}

		// ***************Creating join CustomerCoupon table**********

		try {
			Statement stmt = con.createStatement();

			sql = "create table CustomerCoupon (" + "customerId bigint not null references Customer(id), "
					+ "couponId bigint not null references Coupon(id), " + "primary key(customerId, couponId))";
			stmt.executeUpdate(sql);
			System.out.println("create CustomerCoupon table success :D");
		} catch (SQLException e) {
		}

		// ****************Creating join CompanyCoupon table**********

		try {
			Statement stmt = con.createStatement();

			sql = "create table CompanyCoupon (" + "companyId bigint not null references Company(id), "
					+ "couponId bigint not null references Coupon(id), " + "primary key(companyId, couponId))";
			stmt.executeUpdate(sql);
			System.out.println("create CompanyCoupon table success :D ");
		} catch (SQLException e) {
		}
	}
}
