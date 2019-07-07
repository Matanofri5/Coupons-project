package Main;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.login.LoginException;

import Clients.AdminFacade;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import Customer.CustomerFacade;
import Company.Company;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import Main.DailyTask;

/**
 * @author Linoy & Matan
 * Singleton class coupon system
 * When the system goes up- start the thread that running every 24 hours.
 * When the system goes down- stop the thread.
 * this class knows which facade return by login details.
 */
public class CouponSystem {

	
	private static CouponSystem instance;
	public DailyTask dailyTask;
	public Thread thread;
	public Connection connection;
	private CompanyDAO companyDAO = new CompanyDBDAO();
	private CustomerDAO customerDAO = new CustomerDBDAO();
	
	private static final int DAY = 1000 * 3600 * 24;
	private static final int SLEEPTIME = 1 * DAY;

	/**
	 * Private CTOR (Singleton)
	 */
	private CouponSystem() throws Exception {
		// Activate the daily Coupons Deletion Demon (Thread)
		dailyTask = new DailyTask(SLEEPTIME);
		thread = new Thread(dailyTask);
		thread.start();

	}

	/**
	 * @getInstance method - SINGLETON
	 * @return instance
	 */
	public static CouponSystem getInstance() throws Exception {
		if (instance == null)
			instance = new CouponSystem();
		return instance;
	}

	/**
	 * this method login enable access to the system	
	 * @param name, password, clientType
	 * @return facade
	 * @throws Exception, LoginException
	 */
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception, LoginException {

		CouponClientFacade couponClientFacade = null;

		switch (clientType) {
		case ADMIN:
			if (name=="admin" && password=="1234") {
			couponClientFacade = new AdminFacade();
			}
			break;
			
		case COMPANY:
			
			Set<Company>companies = companyDAO.getAllCompanys();
			Iterator<Company> i = companies.iterator();
			
			while (i.hasNext()) {
				Company current = i.next();
				if (current.getCompanyName().equals(name) && current.getPassword().equals(password)) {
					CompanyFacade companyFacade = new CompanyFacade(current);
					return companyFacade;
				}else if (!i.hasNext()) {
					throw new LoginException("Login Falied! Invalid User or Password!");
				}
			}

			break;
		case CUSTOMER:
		
			Set<Customer>customers = customerDAO.getAllCustomer();
			Iterator<Customer> c = customers.iterator();
			
			while (c.hasNext()) {
				Customer current2 = c.next();
				if (current2.getCustomerName().equals(name) && current2.getPassword().equals(password)) {
					CustomerFacade customerFacade = new CustomerFacade(current2);
					return customerFacade;
				}else if (!c.hasNext()) {
					throw new LoginException("Login Falied! Invalid User or Password!");
				}
			}
			
			break;
		default:
			throw new LoginException("Login Falied! Invalid User or Password!");
		}
		if (couponClientFacade == null) {
			throw new LoginException("Login Falied! Invalid User or Password!");
		}else {
			return couponClientFacade;
		}
		}
	
	/**
	 * Shutdown the system. close all the connectionPool and thread.
	 **/
	public void shutdown() throws Exception {

		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			try {
			connectionPool.closeAllConnections(connection);
			}catch (Exception e) {
				System.out.println("Failed to get connection");
			}
		} catch (Exception e) {
			throw new Exception("ERROR! Properly Shut Down Application Failed!");
		}
		dailyTask.stopTask();
	}
}
