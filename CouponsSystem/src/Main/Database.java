package Main;

import java.awt.Window.Type;
import java.io.NotActiveException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.stream.events.NotationDeclaration;

public class Database {

	//********************************גישה לדרייבר***********************************
	
	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}
	
    //********************************גישה לכתובת,כרגע מקומי במחשב********************
	
	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/JBDB;create=true";
	}
	
	//********************************מטודה שיוצרת טבלאות********************
	public static void createTables(Connection con) throws SQLException {
		String sql;
		
		
		//****************************************יצירת טבלת חברות*************************************************

		try {
		Statement stmt = con.createStatement();
		
		sql = "create table Company ("
				+ "Id bigint not null primary key generated always as identity(start with 1, increment by 1), "
				+ "Comp_name varchar(50) not null, " + "Password varchar(50) not null, " + "Email varchar(50) not null)";
		stmt.executeUpdate(sql);
		System.out.println("success:" + sql);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		//****************************************יצירת טבלת לקוחות*************************************************
	
		try {
			Statement stmt = con.createStatement();

		sql = "create table Customer ("
				+ "Id bigint not null primary key generated always as identity(start with 1, increment by 1), "
				+ "Cust_name varchar(50) not null, " + "Password varchar(50) not null)";
		stmt.executeUpdate(sql);
		System.out.println("success:" + sql);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//****************************************יצירת טבלת קופונים*************************************************
        
		try {
    		Statement stmt = con.createStatement();

		sql = "create table Coupon ("
				+ "Id bigint not null primary key generated always as identity(start with 1, increment by 1), "
				+ "Title varchar(50) not null, " + "Start_date Date not null, "  + "End_date Date not null, " + "Amount int not null, "  
				+ "Message varchar(50) not null, " + "Price Double not null, " + "Image varchar(50) not null, " 
				+ "Type varchar(20) not null)";
		stmt.executeUpdate(sql);
		System.out.println("success:" + sql);
        }catch (SQLException e) {
        	System.out.println(e.getMessage());
        }		
	
		}
	}
