package Main;

import java.sql.Connection;
import java.sql.DriverManager;

import Clients.AdminFacade;
import Company.Company;

public class Test2 {

	public static void main(String[] args) throws Exception {
		
		
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(Database.getDBUrl());
		Database.createTables();
		
		
		
		Company p1 = new Company(1, "Hp", "3443345654", "hp@gmail.com");
		Company p2 = new Company(2, "Lg", "987869977", "lg@gmail.com");
		Company p3 = new Company(3, "Sony", "4314134143", "sony@gmail.com");
		Company p4 = new Company(4, "CocaCola", "vsdv", "vsdvsdv");
		Company p5 = new Company(5, "Samsung", "333", "ff");
		
		AdminFacade adminFacade = new AdminFacade();
		adminFacade.createCompany(p1);

	}

}
