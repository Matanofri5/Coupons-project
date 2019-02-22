package Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	// ********************************Access to driver***********************************

	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}

	// ********************************Access to URL, now local on CMD********************

	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/JBDB;create=true";
	}

	// ********************************Creating tables********************
	public static void createTables(Connection con) throws SQLException {
		String sql;

		// ********************************Creating Company table*****************************

		try {
			Statement stmt = con.createStatement();

			sql = "create table Company ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "companyName varchar(50) not null, " + "password varchar(50) not null, "
					+ "email varchar(50) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create company table success :D  " + sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		// ***************************Creating Customer table**********************************

		try {
			Statement stmt = con.createStatement();

			sql = "create table Customer ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "customerName varchar(50) not null, " + "password varchar(50) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create customer table success :D  " + sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		// ********************************Creating Coupon table*********************************

		try {
			Statement stmt = con.createStatement();

			sql = "create table Coupon ("
					+ "id bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "title varchar(50) not null, " + "startDate Date not null, " + "endDate Date not null, "
					+ "amount int not null, " + "message varchar(50) not null, " + "price double not null, "
					+ "image varchar(50) not null, " + "type varchar(20) not null)";
			stmt.executeUpdate(sql);
			System.out.println("create coupon table success :D  " + sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		// ******************************Creating join CustomerCoupon table************************************

		try {
			java.sql.Statement stmt = con.createStatement();

			sql = "create table CustomerCoupon (" + "customerId bigint not null references Customer(id), "
					+ "couponId bigint not null references Coupon(id), " + "primary key(customerId, couponId))";
			stmt.executeUpdate(sql);
			System.out.println("create customercoupon table success :D " + sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}
}
