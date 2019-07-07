package Main;

import java.sql.Connection;
import java.sql.DriverManager;

import Clients.AdminFacade;
import Clients.ClientType;
import Company.Company;
import Company.CompanyFacade;
import Coupon.Coupon;
import Coupon.CouponType;
import Coupon.DateUtils;
import Customer.Customer;
import Customer.CustomerFacade;

public class Test2 {

	private static CouponSystem couponSystem; 

	
	public static void main(String[] args) throws Exception {
		
		
		try {
			couponSystem = CouponSystem.getInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Company a1 = new Company(1, "Hp", "aa11", "hp@gmail.com");
		Company a2 = new Company(2, "Lg", "bb22", "lg@gmail.com");
		Company a3 = new Company(3, "Sony", "cc33", "sony@gmail.com");

		
		Customer b1 = new Customer(1, "Matan", "123");
		Customer b2 = new Customer(2, "Linoy", "456");
		
		Coupon c1 = new Coupon(1, "Teva pharm", DateUtils.getCurrentDate(), DateUtils.getByMounth(), 55, "Teva", 33.5, "website", CouponType.HEALTH);
		Coupon c2 = new Coupon(2, "Chairs", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 66, "Hagor", 12.5, "website", CouponType.CAMPING);
		Coupon c3 = new Coupon(3, "Pizza", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 77, "PizzaHut", 41.2, "website", CouponType.FOOD);
		Coupon c4 = new Coupon(4, "Roller coaster", DateUtils.getCurrentDate(), DateUtils.getByWeek(), 99, "LunaPark", 32.4, "website", CouponType.TRAVELING);
		Coupon c5 = new Coupon(5, "Teva pharm", DateUtils.getCurrentDate(), DateUtils.getByTwoWeeks(), 0, "Teva", 4444.7, "website", CouponType.HEALTH);
		
		Database.dropTables();
		Database.createTables();
		
		AdminFacade adminFacade;
		
		try {
			adminFacade = (AdminFacade)couponSystem.login("admin", "1234", ClientType.ADMIN);
			
			adminFacade.createCompany(a1);
			adminFacade.createCompany(a1);
			
			adminFacade.createCustomer(b1);
			adminFacade.createCustomer(b2);
			
		} catch (Exception e) {
			System.out.println("failed connect as admin");
		}
		
		CompanyFacade companyFacade;
		
		try {
			companyFacade = (CompanyFacade)couponSystem.login("Hp", "aa11", ClientType.COMPANY);
			companyFacade.createCoupon(c1);
			companyFacade.createCoupon(c2);
			companyFacade.createCoupon(c3);
			companyFacade.createCoupon(c4);
			companyFacade.createCoupon(c5);
			
			System.out.println(companyFacade.getCouponbyType(CouponType.HEALTH));
		} catch (Exception e) {
			System.out.println("failed connect as company");
		}
		
		CustomerFacade customerFacade;
		
		try {
			customerFacade = (CustomerFacade)couponSystem.login("Matan", "123", ClientType.CUSTOMER);
			customerFacade.purchaseCoupon(1);
		} catch (Exception e) {
			System.out.println("failed connect as customer");
		}
	}

}
