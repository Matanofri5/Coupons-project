package Main;

import java.sql.Connection;

import Clients.AdminFacade;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Customer.CustomerDAO;
import Customer.CustomerDBDAO;
import Customer.CustomerFacade;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Company.CompanyFacade;
import Main.DailyTask;

public class CouponSystem {

	private static CouponSystem instance;
	public DailyTask dailyTask;
	public Thread thread;
	public Connection connection;
	
	private static final int DAY = 1000 * 3600 * 24;
	private static final int SLEEPTIME = 1 * DAY;

	private CouponSystem() throws Exception {
		// Activate the daily Coupons Deletion Demon (Thread)
		dailyTask = new DailyTask(SLEEPTIME);
		thread = new Thread(dailyTask);
		thread.start();
		System.out.println("Welcome to CouponSystem");
//		try {
//			Database.createTables(connection);
//		} catch (Exception e) {
//			throw new Exception("tables already exists :(");
//		}
	}

	public static CouponSystem getInstance() throws Exception {
		if (instance == null)
			instance = new CouponSystem();
		return instance;
	}

	public CouponClientFacade login(String user, String pass, ClientType clientType) throws Exception {

		CouponClientFacade couponClientFacade = null;

		switch (clientType) {
		case ADMIN:
			couponClientFacade = new AdminFacade();
			break;
		case COMPANY:
			couponClientFacade = new CompanyFacade();
			break;
		case CUSTOMER:
			couponClientFacade = new CustomerFacade();
			break;
		default:
			couponClientFacade = null;
		}
		if (couponClientFacade != null) {
			return couponClientFacade;
		} else {
			throw new Exception("Login Falied! Invalid User or Password!");
		}
	}

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
