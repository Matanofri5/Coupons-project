package Main;

import java.awt.Window.Type;
import java.util.HashSet;
import java.util.Set;
import Clients.AdminFacade;
import Clients.ClientType;
import Company.Company;
import Customer.Customer;

public class TheTest {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Welcome to CouponSystem");

		//Connecting to couponSystem singelton and start dailyTask to delete expired coupons !
		CouponSystem couponSystem = CouponSystem.getInstance();
		
		String name;
		String password;
		ClientType clientType = ClientType.ADMIN;
		Set<Company> companies = new HashSet<Company>();
		Set<Customer> customers = new HashSet<Customer>();



		
		try {
			System.out.println("You have "+ConnectionPool.getInstance().getConnection().getTransactionIsolation()+" available connections");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		//***Admin test***//
		
		// Bad login //
//		name = "admin";
//		password = "12354";
//		System.out.println("Checking bad login as Admin: ");
//		try {
//			couponSystem.login(name, password, clientType);
//		} catch (Exception e) {
//			throw new Exception("Failed to login as Admin, name or password are wrong !");
//		}
		
		// Good login //
		name = "admin";
		password = "1234";
		try {
			couponSystem.login(name, password, clientType);
			System.out.println("*******************Logged ad Admin*******************\n");
			Database.dropTables();
			Database.createTables();
			
			AdminFacade adminFacade = new AdminFacade();

			// Creating 5 companies //
			Company a1 = new Company(1, "Hp", "aa11", "hp@gmail.com");
			Company a2 = new Company(2, "Lg", "bb22", "lg@gmail.com");
			Company a3 = new Company(3, "Sony", "cc33", "sony@gmail.com");
			Company a4 = new Company(4, "CocaCola", "dd44", "cocacola@gamil.com");
			Company a5 = new Company(5, "Samsung", "ee55", "samsung@gamil.com");
						
			// Admin create companies //
			adminFacade.createCompany(a1);
			adminFacade.createCompany(a2);
			adminFacade.createCompany(a3);
			adminFacade.createCompany(a4);
			adminFacade.createCompany(a5);
			
			 System.err.println("**********Companies by Admin**********\n");

			// Show all new companies //
			System.err.println("Show all companies");
			companies = adminFacade.getAllCompanys();
			System.out.println(companies + "\n");
			
			// Admin update company //
			adminFacade.updateCompany(a1, "123abc", "newpassword");
			System.out.println("Company " + a1.getCompanyName() + " updated !");
			
			// show company after update //
			adminFacade.getCompany(1);
			System.out.println("This company was updated --> " + a1 + " \n");
			
			// Admin delete company //
			adminFacade.removeCompany(a5);
			System.out.println("Admin removed company " + a5.getCompanyName() + "!");
			
			// Get all companies after update and delete //
			System.err.println("Show all companies after update and delete");
			companies = adminFacade.getAllCompanys();
			System.out.println(companies + " \n\n");
			
			 System.err.println("**********Customers by Admin**********\n");
			
			// Creating 5 customers //
			Customer b1 = new Customer(1, "Matan", "123");
			Customer b2 = new Customer(2, "Linoy", "456");
			Customer b3 = new Customer(3, "Dvir", "678");
			Customer b4 = new Customer(4, "Ehud", "910");
			Customer b5 = new Customer(5, "Or", "658");
			
			// Admin create customers //
			adminFacade.createCustomer(b1);
			adminFacade.createCustomer(b2);
			adminFacade.createCustomer(b3);
			adminFacade.createCustomer(b4);
			adminFacade.createCustomer(b5);
			
			// Show all customers //
			System.err.println("Show all customers");
			customers = adminFacade.getAllCustomers();
			System.out.println(customers + "\n");
			
			// Admin update customer //
			adminFacade.updateCustomer(b1, "123456789");
			System.out.println("Customer " + b1.getCustomerName() + " updated !");
			
			// Show customer after update //
			adminFacade.getCustomer(1);
			System.out.println("This customer was updated --> " + b1 + " \n");	
			
			// Admin delete customer //
			adminFacade.removeCustomer(b5);
			System.out.println("Admin removed customer " + b5.getCustomerName() + "!");
			
			// Get all customer after update and delete //
			System.err.println("Show all customer after update and delete");
			customers = adminFacade.getAllCustomers();
			System.out.println(customers + "\n");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
		
		//***Company test***//
//		name = null;
//		password = null;
		
		// Bad login //
//		name = "Hp";
//		password = "aa12";
//		System.out.println("Checking bad login as Company: ");
//		try {
//			couponSystem.login(name, password, clientType);
//		} catch (Exception e) {
//			throw new Exception("Failed to login as Company, name or password are wrong !");
//		}
		
		// Good login //
		name = "Hp";
		password = "aa11";
		clientType = ClientType.COMPANY;
		try {
			couponSystem.login(name, password, clientType);
			System.out.println("*******************Logged ad Company*******************\n");
			
			
			
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
		
		
	}
}
