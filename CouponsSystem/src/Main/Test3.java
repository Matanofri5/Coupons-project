package Main;

import java.net.ConnectException;
import java.util.HashSet;
import java.util.Set;
import Clients.AdminFacade;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Company.Company;
import Company.CompanyFacade;
import Coupon.Coupon;
import Coupon.CouponFacade;
import Coupon.CouponType;
import Customer.Customer;
import Customer.CustomerFacade;
import Coupon.DateUtils;

public class Test3 {

	public static void main(String[] args) throws Exception {

		String name;
		String password;
//		ClientType typeAdmin = ClientType.ADMIN;
//		ClientType typeCompany = ClientType.COMPANY;
//		ClientType typeCustomer = ClientType.CUSTOMER;
		Clients.ClientType type = Clients.ClientType.ADMIN;
		Set<Company> companies = new HashSet<Company>();
		Set<Customer> customers = new HashSet<Customer>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		Company company;
		Customer customer;
		AdminFacade adminFacade = new AdminFacade();
		CompanyFacade companyFacade = new CompanyFacade();
		CustomerFacade customerFacade = new CustomerFacade();

		CouponSystem couponSystem = CouponSystem.getInstance();

		//AdminFacade admin = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);

		/******* dropping tables *******/
		Database.dropTables();
		/******* creating tables *******/
		Database.createTables();

		Company a1 = new Company();
		a1.setCompanyName("hp");
		a1.setEmail("hp@gmail.com");
		a1.setPassword("hphp");
		//System.out.println(a1);
		Company a2 = new Company(2, "Lg", "LgLg", "lg@gmail.com");
		Company a3 = new Company(3, "Sony", "SonySony", "sony@gmail.com");
		Company a4 = new Company(4, "CocaCola", "CocaColaCocaCola", "CocaCola@gmail.com");
		Company a5 = new Company(5, "Samsung", "SamsungSamsung", "Samsung@gmail.com");


		Customer b1 = new Customer();
		b1.setCustomerName("linoy");
		b1.setPassword("linoylinoy");
		//System.out.println(b1);
		Customer b2 = new Customer(2, "Matan", "MatanMatan");
		Customer b3 = new Customer(3, "Bar", "BarBar");
		Customer b4 = new Customer(4, "Dvir", "DvirDvir");
		Customer b5 = new Customer(5, "Ehud", "EhudEhud");

		
		Coupon c1 = new Coupon();
		c1.setTitle("test");
		c1.setStartDate(DateUtils.getCurrentDate());
		c1.setEndDate(DateUtils.getByMounth());
		c1.setAmount(50);
		c1.setMessage("hello");
		c1.setPrice(33.2);
		c1.setImage("photo");
		c1.setType(CouponType.FOOD);
		//System.out.println(c1);
		Coupon c2 = new Coupon(2, "test1", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 66, "camp", 36.7, "picture", CouponType.CAMPING);
		Coupon c3 = new Coupon(3, "test2", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 77, "food", 41.2, "photo", CouponType.FOOD);
		Coupon c4 = new Coupon(4, "test3", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 99, "bla", 32.4, "uy", CouponType.TRAVELING);
		Coupon c5 = new Coupon(5, "test4", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 44, "ff", 4444.7, "gg", CouponType.ELECTRICITY);
		Coupon c6 = new Coupon(6, "test5", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 52, "bbb", 66.1, "dd", CouponType.HEALTH);
		Coupon c7 = new Coupon(7, "test6", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 34, "fd", 11.2, "fd", CouponType.HEALTH);
		Coupon c8 = new Coupon(8, "test10", DateUtils.getCurrentDate(), DateUtils.getByTwoMountsAgo(), 55, "fsf", 55/5, "Dasd", CouponType.FOOD);

		try {
			System.out
					.println("you have " + ConnectionPool.getInstance().getConnection() + " available connections :)");
		} catch (ConnectException e) {
			System.out.println(e.getMessage());
		}
		
        /***************Admin Test****************/

        //(1) bad login
		
		/**--------**/
		
	    //(2)good login and get Admin facade
		
		/**--------**/

		 //(3) add a costumer
		System.err.println("----add a costumer----");
		adminFacade.addCustomer(b1);
		System.out.println(b1);
		adminFacade.addCustomer(b2);
		System.out.println(b2);
		
		//same name- failed
		adminFacade.addCustomer(b1);
		
		 //(4) get a costumer by id
		System.err.println("----get a costumer by id----");
		System.out.println(adminFacade.getCustomer(1));
		
		// (5) get all costumers
		System.err.println("----get all costumers----");
		System.out.println(adminFacade.getAllCustomers());
		
		 //(6) update a costumer (only password)
		System.err.println("----update a costumer----");
		System.out.println(b1);
		adminFacade.updateCustomer(b1, "newPassword");
		System.out.println("change to -->  "+b1);
		
		 //(7) remove a costumer
		adminFacade.removeCustomer(b2);

		 //(8) add a company
		System.err.println("----add a company----");
		adminFacade.addCompany(a1);
		System.out.println(a1);
		adminFacade.addCompany(a2);
		System.out.println(a2);
		
		//same name- failed
		adminFacade.addCompany(a1);
		
		 //(9) get a company by id
		System.err.println("----get a company by id----");
		System.out.println(adminFacade.getCompany(1));

        // (10) get all companies
		System.err.println("----get all companies----");
		System.out.println(adminFacade.getAllCompanys());	
		
		 //(11) update a company (without company name!)
		System.err.println("----update a company----");
		System.out.println(a2);
		adminFacade.updateCompany(a2, "newPassword", "newEmail");
		System.out.println("change to -->  "+a2);
		
		 //(12) remove a company
		adminFacade.removeCompany(a1);

        /***************company Test****************/

		
		
		
        /***************customer Test****************/

	}

}
