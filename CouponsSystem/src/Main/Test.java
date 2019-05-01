package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import Clients.AdminFacade;
import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import CompanyCoupon.CompanyCoupon;
import CompanyCoupon.CompanyCouponFacade;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponFacade;
import Coupon.CouponType;
import Customer.Customer;
import Customer.CustomerFacade;
import CustomerCoupon.CustomerCoupon;
import Coupon.DateUtils;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponFacade;

public class Test {

	
	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");
		Connection con = DriverManager.getConnection(Database.getDBUrl());

		/**
		 * initialization objects.
		 * @param admin
		 * @param coupon
		 * @param company
		 * @param customer
		 * @param companycoupon
		 * @param customercoupon
		 */
		
		AdminFacade adminFacade = new AdminFacade();
		CouponFacade couponFacade = new CouponFacade();
		CompanyFacade companyFacade = new CompanyFacade();
		CustomerFacade customerFacade = new CustomerFacade();
		CompanyCouponFacade companyCouponFacade = new CompanyCouponFacade();
		CustomerCouponFacade customerCouponFacade = new CustomerCouponFacade();
		
		/*********************************** dropping tables **************************/
		customerCouponFacade.dropTable();
		companyCouponFacade.dropTable();
		companyFacade.dropTable();
		customerFacade.dropTable();
		couponFacade.dropTable();
		
		/*********************************** creating tables *************************/
		
		Database.createTables(con);
		
		/*************************************** Company ****************************/

		Company p1 = new Company(1, "HP", "3443345654", "hp@gmail.com");
		Company p2 = new Company(2, "Lg", "987869977", "lg@gmail.com");
		Company p3 = new Company(3, "Sony", "4314134143", "sony@gmail.com");
		Company p4 = new Company(4, "CocaCola", "vsdv", "vsdvsdv");
		Company p5 = new Company(5, "HP", "333", "ff");
		
		/*************************************** Customer ****************************/

		Customer c1 = new Customer(1, "Matan", "3784628");
		Customer c2 = new Customer(2, "Bar", "1325266");
		Customer c3 = new Customer(3, "Dvir", "9879886");
		Customer c4 = new Customer(4, "Dvir", "fdfdasadf");
		Customer c5 = new Customer(5, "Matan", "ddd");
		
		/*************************************** Coupon ****************************/

		Coupon u1 = new Coupon(1, "test", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 55, "sick", 33.5, "image", CouponType.HEALTH);
		Coupon u2 = new Coupon(2, "test2", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 66, "camp", 36.7, "picture", CouponType.CAMPING);
		Coupon u3 = new Coupon(3, "test3", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 77, "food", 41.2, "photo", CouponType.FOOD);
		Coupon u4 = new Coupon(4, "test4", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 99, "bla", 32.4, "uy", CouponType.TRAVELING);
		Coupon u5 = new Coupon(5, "test", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 44, "ff", 4444.7, "gg", CouponType.ELECTRICITY);
		Coupon u6 = new Coupon(6, "test", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 52, "bbb", 66.1, "dd", CouponType.HEALTH);
		Coupon u7 = new Coupon(7, "testi", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 22, "fd", 11.2, "fd", CouponType.HEALTH);
		
		/**************************************** CustomerCoupon****************************/

		CustomerCoupon a1 = new CustomerCoupon(1, 2);
		CustomerCoupon a2 = new CustomerCoupon(9, 11);
		CustomerCoupon a3 = new CustomerCoupon(15,40);
		CustomerCoupon a4 = new CustomerCoupon(16, 41);
		CustomerCoupon a5 = new CustomerCoupon(17, 42);
		CustomerCoupon a6 = new CustomerCoupon(26, 43);
		CustomerCoupon a7 = new CustomerCoupon(20, 46);
		
		/*****************************************CompanyCoupon******************************/
		
		CompanyCoupon b1 = new CompanyCoupon(1, 6);
		CompanyCoupon b2 = new CompanyCoupon(1, 11);
		CompanyCoupon b3 = new CompanyCoupon(4, 11);
		CompanyCoupon b4 = new CompanyCoupon(8, 38);
		CompanyCoupon b5 = new CompanyCoupon(9, 46);
		
		
		
		
//		 companyFacade.insertCompany(p1);
//		 companyFacade.insertCompany(p2);
//		 companyFacade.insertCompany(p3);
//		 companyFacade.insertCompany(p4);
//		 companyFacade.insertCompany(p5);


//		 customerFacade.insertCustomer(c1);
//		 customerFacade.insertCustomer(c2);
//		 customerFacade.insertCustomer(c3);
//		 customerFacade.insertCustomer(c4);
//		 customerFacade.insertCustomer(c5);

		
//		 couponFacade.insertCoupon(u1);
//		 couponFacade.insertCoupon(u2);
//		 couponFacade.insertCoupon(u3);
//		 couponFacade.insertCoupon(u4);
//		 couponFacade.insertCoupon(u5);
//		 couponFacade.insertCoupon(u6);
//		 couponFacade.insertCoupon(u7);


//		 customerCouponFacade.insertCustomerCoupon(a1);
//		 customerCouponFacade.insertCustomerCoupon(a2);
//		 customerCouponFacade.insertCustomerCoupon(a3);

//		companyCouponFacade.insertCompanyCoupon(b1);
//		companyCouponFacade.insertCompanyCoupon(b2);
//		companyCouponFacade.insertCompanyCoupon(b3);
//		companyCouponFacade.insertCompanyCoupon(b4);

		
		



	}
}
