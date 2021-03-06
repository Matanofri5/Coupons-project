//package Main;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.security.auth.login.LoginException;
//
//import Clients.AdminFacade;
//import Clients.ClientType;
//import Clients.CouponClientFacade;
//import Company.Company;
//import Company.CompanyFacade;
//import Coupon.Coupon;
//import Coupon.CouponFacade;
//import Coupon.CouponType;
//import Customer.Customer;
//import Customer.CustomerFacade;
//import Coupon.DateUtils;
//
//public class Test {
//
//	
//	public static void main(String[] args) throws Exception {
//
//		/**
//		 * getInstance to create the DB tables & start the daily task
//		 */
//		
//		Set<Company> companies = new HashSet<Company>();
//		Set<Customer> customers = new HashSet<Customer>();
//		Set<Coupon> coupons = new HashSet<Coupon>();
//		
//		CouponClientFacade facade;
//		
//		CouponSystem couponSystem = CouponSystem.getInstance();
//		ConnectionPool.getInstance().getConnection();
//		 //****Admin Test****//
//
//        //(1) bad login//
////		try {
////			facade = couponSystem.login("admin", "12354", ClientType.ADMIN);
////		} catch (LoginException e) {
////			throw new LoginException("failed to login");
////		}
//
////		try {
////			facade = couponSystem.login("admin", "1234", ClientType.ADMIN);
////		} catch (LoginException e) {
////			System.out.println(e.getMessage());
////		}
//		
//		
//		/**
//		 * initialization facade objects.
//		 * @param admin
//		 * @param coupon
//		 * @param company
//		 * @param customer
//		 */
//		
//		AdminFacade adminFacade = new AdminFacade();
//		CouponFacade couponFacade = new CouponFacade();
//		CompanyFacade companyFacade = new CompanyFacade();
//		CustomerFacade customerFacade = new CustomerFacade();
//		
//		
////		/**
////		 * initialization dao objects to dbdao.
////		 * @param coupondao
////		 * @param companydao
////		 * @param customerdao
////		 */
////		CouponDAO couponDAO = new CouponDBDAO();
////		CompanyDAO companyDAO = new CompanyDBDAO();
////		CustomerDAO customerDAO = new CustomerDBDAO();
//		
//		
//		/******* dropping tables *******/
//		Database.dropTables();
//		/******* creating tables *******/
//		Database.createTables();
//		/*************************************** Company ****************************/
//
//		Company a1 = new Company(1, "Hp", "3443345654", "hp@gmail.com");
//		Company a2 = new Company(2, "Lg", "987869977", "lg@gmail.com");
//		Company a3 = new Company(3, "Sony", "4314134143", "sony@gmail.com");
//		Company a4 = new Company(4, "CocaCola", "vsdv", "vsdvsdv");
//		Company a5 = new Company(5, "Samsung", "333", "ff");
//		
//		/*************************************** Customer ****************************/
//
//		Customer b1 = new Customer(1, "Matan", "3784628");
//		Customer b2 = new Customer(2, "Bar", "1325266");
//		Customer b3 = new Customer(3, "Dvir", "9879886");
//		Customer b4 = new Customer(4, "Ehud", "fdfdasadf");
//		Customer b5 = new Customer(5, "Or", "ddd");
//		
//		/*************************************** Coupon ****************************/
//
//		Coupon c1 = new Coupon(1, "test", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 55, "sick", 33.5, "image", CouponType.HEALTH);
//		Coupon c2 = new Coupon(2, "test1", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 66, "camp", 36.7, "picture", CouponType.CAMPING);
//		Coupon c3 = new Coupon(3, "test2", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 77, "food", 41.2, "photo", CouponType.FOOD);
//		Coupon c4 = new Coupon(4, "test3", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 99, "bla", 32.4, "uy", CouponType.TRAVELING);
//		Coupon c5 = new Coupon(5, "test4", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 44, "ff", 4444.7, "gg", CouponType.ELECTRICITY);
//		Coupon c6 = new Coupon(6, "test5", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 52, "bbb", 66.1, "dd", CouponType.HEALTH);
//		Coupon c7 = new Coupon(7, "test6", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 34, "fd", 11.2, "fd", CouponType.HEALTH);
//		Coupon c8 = new Coupon(8, "test10", DateUtils.getCurrentDate(), DateUtils.getByTwoMountsAgo(), 55, "fsf", 55/5, "Dasd", CouponType.FOOD);
//	
//		adminFacade.createCompany(a1);
//		adminFacade.createCompany(a2);
//		adminFacade.createCompany(a3);
//		companyFacade.createCoupon(a1, c1);
//		companyFacade.createCoupon(a1, c2);
//		companyFacade.createCoupon(a1, c8);
//
//		adminFacade.createCustomer(b1);
//		adminFacade.createCustomer(b2);
//		adminFacade.createCustomer(b3);
//		customerFacade.purchaseCoupon(b1, 1);
//		
//		adminFacade.removeCompany(a1);
//
//		
//
//		
//
//	
//		
//	}
//}
