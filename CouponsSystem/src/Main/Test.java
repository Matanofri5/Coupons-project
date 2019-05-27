package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.FormatterClosedException;
import java.util.HashSet;
import java.util.Set;

import Clients.AdminFacade;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import CompanyCoupon.CompanyCoupon;
import CompanyCoupon.CompanyCouponDAO;
import CompanyCoupon.CompanyCouponDBDAO;
import CompanyCoupon.CompanyCouponFacade;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponFacade;
import Coupon.CouponType;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import Customer.CustomerFacade;
import CustomerCoupon.CustomerCoupon;
import Coupon.DateUtils;
import CustomerCoupon.CustomerCoupon;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;
import CustomerCoupon.CustomerCouponFacade;

public class Test {

	
	public static void main(String[] args) throws Exception {

		/**
		 * getInstance to create the DB tables & start the daily task
		 */
		
		Set<Company> companies = new HashSet<Company>();
		Set<Customer> customers = new HashSet<Customer>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		
		CouponClientFacade facade;
		Database.dropTables();
		Database.createTables();


		CouponSystem couponSystem = CouponSystem.getInstance();
		ConnectionPool.getInstance().getConnection();
		
		
		facade = couponSystem.login("admin", "1234", ClientType.ADMIN);
		if (facade instanceof AdminFacade) {
			System.out.println("success");
		}else {
			System.out.println("gala");
		}
		
		
//		Class.forName("org.apache.derby.jdbc.ClientDriver");
//		Connection con = DriverManager.getConnection(Database.getDBUrl());

		/**
		 * initialization facade objects.
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
		
		/**
		 * initialization dao objects to dbdao.
		 * @param companydao
		 * @param customerdao
		 */
		CouponDAO couponDAO = new CouponDBDAO();
		CompanyDAO companyDAO = new CompanyDBDAO();
		CustomerDAO customerDAO = new CustomerDBDAO();
		CompanyCouponDAO companyCouponDAO = new CompanyCouponDBDAO();
		CustomerCouponDAO customerCouponDAO = new CustomerCouponDBDAO();
		
		/*********************************** dropping tables **************************/
//		customerCouponFacade.dropTable();
//		companyCouponFacade.dropTable();
//		companyFacade.dropTable();
//		customerFacade.dropTable();
//		couponFacade.dropTable();
//		Database.dropTables();
		
		/*********************************** creating tables *************************/
//		Database.createTables();
		
		/*************************************** Company ****************************/

		Company p1 = new Company(1, "Hp", "3443345654", "hp@gmail.com");
		Company p2 = new Company(2, "Lg", "987869977", "lg@gmail.com");
		Company p3 = new Company(3, "Sony", "4314134143", "sony@gmail.com");
		Company p4 = new Company(4, "CocaCola", "vsdv", "vsdvsdv");
		Company p5 = new Company(5, "Samsung", "333", "ff");
		
		/*************************************** Customer ****************************/

		Customer c1 = new Customer(1, "Matan", "3784628");
		Customer c2 = new Customer(2, "Bar", "1325266");
		Customer c3 = new Customer(3, "Dvir", "9879886");
		Customer c4 = new Customer(4, "Ehud", "fdfdasadf");
		Customer c5 = new Customer(5, "Or", "ddd");
		
		/*************************************** Coupon ****************************/

		Coupon u1 = new Coupon(1, "test", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 55, "sick", 33.5, "image", CouponType.HEALTH);
		Coupon u2 = new Coupon(2, "test1", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 66, "camp", 36.7, "picture", CouponType.CAMPING);
		Coupon u3 = new Coupon(3, "test2", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 77, "food", 41.2, "photo", CouponType.FOOD);
		Coupon u4 = new Coupon(4, "test3", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 99, "bla", 32.4, "uy", CouponType.TRAVELING);
		Coupon u5 = new Coupon(5, "test4", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 44, "ff", 4444.7, "gg", CouponType.ELECTRICITY);
		Coupon u6 = new Coupon(6, "test5", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 52, "bbb", 66.1, "dd", CouponType.HEALTH);
		Coupon u7 = new Coupon(7, "test6", DateUtils.getCurrentDate(), DateUtils.getExpiredDate(), 34, "fd", 11.2, "fd", CouponType.HEALTH);
		
		/**************************************** CustomerCoupon****************************/

		CustomerCoupon a1 = new CustomerCoupon(1, 1);
		CustomerCoupon a2 = new CustomerCoupon(1, 2);
		CustomerCoupon a3 = new CustomerCoupon(1, 3);
		CustomerCoupon a4 = new CustomerCoupon(2, 2);
		CustomerCoupon a5 = new CustomerCoupon(2, 4);
		CustomerCoupon a6 = new CustomerCoupon(4, 1);
		CustomerCoupon a7 = new CustomerCoupon(4, 2);
		
		/*****************************************CompanyCoupon******************************/
		
		CompanyCoupon b1 = new CompanyCoupon(1, 1);
		CompanyCoupon b2 = new CompanyCoupon(1, 2);
		CompanyCoupon b3 = new CompanyCoupon(1, 3);
		CompanyCoupon b4 = new CompanyCoupon(2, 4);
		CompanyCoupon b5 = new CompanyCoupon(2, 6);
		CompanyCoupon b6 = new CompanyCoupon(5, 1);
		CompanyCoupon b7 = new CompanyCoupon(2, 1);
		
				
		adminFacade.addCompany(p1);
		adminFacade.addCompany(p2);
		adminFacade.addCompany(p3);
		adminFacade.addCompany(p4);
		adminFacade.addCompany(p5);

		
		adminFacade.addCustomer(c1);
		adminFacade.addCustomer(c2);
		adminFacade.addCustomer(c3);
		adminFacade.addCustomer(c4);
		adminFacade.addCustomer(c5);

		 couponFacade.insertCoupon(u1);
		 couponFacade.insertCoupon(u2);
		 couponFacade.insertCoupon(u3);
		 couponFacade.insertCoupon(u4);
		 couponFacade.insertCoupon(u5);
		 couponFacade.insertCoupon(u6);
		 couponFacade.insertCoupon(u7);


		 customerCouponFacade.insertCustomerCoupon(a1);
		 customerCouponFacade.insertCustomerCoupon(a2);
		 customerCouponFacade.insertCustomerCoupon(a3);
		 customerCouponFacade.insertCustomerCoupon(a4);
		 customerCouponFacade.insertCustomerCoupon(a5);
		 customerCouponFacade.insertCustomerCoupon(a6);
		 customerCouponFacade.insertCustomerCoupon(a7);

		
		companyCouponFacade.insertCompanyCoupon(b1);
		companyCouponFacade.insertCompanyCoupon(b2);
		companyCouponFacade.insertCompanyCoupon(b3);
		companyCouponFacade.insertCompanyCoupon(b4);
		companyCouponFacade.insertCompanyCoupon(b5);
		companyCouponFacade.insertCompanyCoupon(b6);
		companyCouponFacade.insertCompanyCoupon(b7);

		
//		System.out.println(companyFacade.getAllCouponsByType(CouponType.HEALTH));
		
//		System.out.println(companyFacade.getAllCouponsByType(CouponType.HEALTH));

		adminFacade.updateCompany(p5, "ddd", "sds");
		
		
		
		}
}
