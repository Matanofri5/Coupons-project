package Main;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.derby.client.am.DateTime;

import Company.Company;
import Company.CompanyFacade;
import Coupon.Coupon;
import Coupon.CouponType;
import Customer.Customer;
import Customer.CustomerFacade;

public class Test {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(Database.getDBUrl());

		Database.createTables(con);
	    /***************************************Company****************************/

		Company p1 = new Company(1,"HP","3443345654","hp@gmail.com");
		Company p2 = new Company(2,"Lg","987869977","lg@gmail.com");
		Company p3 = new Company(3,"Sony","4314134143","sony@gmail.com");
	
		CompanyFacade companyFacade = new CompanyFacade();
//		companyFacade.insertCompany(p1);
//		companyFacade.insertCompany(p2);
//	    companyFacade.insertCompany(p3);
	    
//		companyFacade.updateCompany(p3, 2, "fdsg", "fsdg", "dgzdg");
//		companyFacade.updateCompany(p1, 7, "llll", "ffff", "54554");
//		companyFacade.updateCompany(p3, 8, "tttt", "uuu", "scas");
//		companyFacade.updateCompany(p1, 9, "ereee", "cccc", "nnn");
//		companyFacade.updateCompany(p3, 10, "www", "mmmm", "dvvvvv");
//		companyFacade.updateCompany(p1, 16, "aaaaaa", "bbbb", ";;;;;");
		
//      companyFacade.dropTable();
//	    companyFacade.removeCompany(p1);
//	    companyFacade.removeCompany(p2);
//	    companyFacade.removeCompany(p3);
//	    System.out.println(companyFacade.getAllCompany());		
	    
	    
	    /***************************************Customer****************************/
	    
	   Customer c1 = new Customer(4, "Matan", "3784628");
	   Customer c2 = new Customer(5, "Bar", "1325266");
	   Customer c3 = new Customer(6, "Dvir", "9879886");
	   

	   CustomerFacade customerFacade = new CustomerFacade();
//	   customerFacade.insertCustomer(c1);
//	   customerFacade.insertCustomer(c2);
//	   customerFacade.insertCustomer(c3);
	   
	   customerFacade.updateCustomer(c1, 6, "levi", "nnnn");
	    
	    /***************************************Coupon****************************/

//	   Date date = new Date();
//	   SimpleDateFormat Date = new SimpleDateFormat("dd-mm-yyyy");
	  
	   
//	   Coupon e1 = new Coupon(7, "medical", "03-06-2020", "03-07-2020", 73, "medisen", 51.6, "it",CouponType.HEALTH);
	   
		}
}
