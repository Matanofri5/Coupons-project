package Main;

import java.awt.Window.Type;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.derby.client.am.DateTime;
import Company.Company;
import Company.CompanyFacade;
import Coupon.Coupon;
import Coupon.CouponFacade;
import Coupon.CouponType;
import Customer.Customer;
import Customer.CustomerFacade;
import Coupon.DateUtils;

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
		
//		companyFacade.updateCompany(p3, 3, "fdsg", "fsdg", "dgzdg");
//		companyFacade.updateCompany(p1, 7, "llll", "ffff", "54554");
//		companyFacade.updateCompany(p3, 8, "tttt", "uuu", "scas");

		
//	    companyFacade.removeCompany(7);
//	    companyFacade.removeCompany(5);
//	    companyFacade.removeCompany(6);
		
//      companyFacade.dropTable();
//        System.out.println(companyFacade.getAllCompany());		
	    
	    
	    /***************************************Customer****************************/
	    
	   Customer c1 = new Customer(4, "Matan", "3784628");
	   Customer c2 = new Customer(5, "Bar", "1325266");
	   Customer c3 = new Customer(6, "Dvir", "9879886");
//	   
//
	   CustomerFacade customerFacade = new CustomerFacade();
//	   customerFacade.insertCustomer(c1);
//	   customerFacade.insertCustomer(c2);
//	   customerFacade.insertCustomer(c3);
	   
//	   customerFacade.updateCustomer(c2, 3, "Ehud", "4455566");
	   
//	   customerFacade.removeCustomer(c2);
//	   customerFacade.removeCustomer(c1);
//	   customerFacade.removeCustomer(c3);
	   
//	   customerFacade.dropTable();    
//	   customerFacade.updateCustomer(c1, 6, "levi", "nnnn");
	    
	    /***************************************Coupon****************************/
       
       Coupon u1 = new Coupon(7, "test", DateUtils.getcurrentdate(), DateUtils.getExpiredDate(), 55, "sick", 33.5, "image", CouponType.HEALTH);
	   Coupon u2 = new Coupon(8, "test2", DateUtils.getcurrentdate(), DateUtils.getExpiredDate(), 66, "camp", 36.7, "picture", CouponType.CAMPING);
	   Coupon u3 = new Coupon(9, "test3", DateUtils.getcurrentdate(), DateUtils.getExpiredDate(), 77, "food", 41.2, "photo", CouponType.FOOD);
     
	   CouponFacade couponFacade = new CouponFacade();
       
//       couponFacade.insertCoupon(u1);
//       couponFacade.insertCoupon(u2);
//       couponFacade.insertCoupon(u3);
       
       couponFacade.updateCoupon(u1, 2, "test4", DateUtils.getcurrentdate(), DateUtils.getExpiredDate(), 88, "lamp", 73.6, "light", CouponType.ELECTRICITY);
//         couponFacade.removeCoupon(1);
//         couponFacade.removeCoupon(2);
//         couponFacade.removeCoupon(4);
       
//           couponFacade.dropTable();
	    
	    
	    
	    
		}
}
