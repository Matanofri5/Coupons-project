package Main;

import java.sql.Connection;

import Clients.AdminFacade;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Customer.CustomerFacade;
import Company.CompanyFacade;

public class CouponSystem {

	private static CouponSystem instance;
	public DailyTask dailyTask;
	public Thread thread;
	public Connection connection;

	public CouponSystem() throws Exception {
		// Activate the daily Coupons Deletion Demon (Thread)
		dailyTask = new DailyTask();
		thread = new Thread();
		thread.start();
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
			couponClientFacade = (CouponClientFacade) new CompanyFacade();
			break;
		case CUSTOMER:
			couponClientFacade = (CouponClientFacade) new CustomerFacade();
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
			connectionPool.closeAllConnections(connection);
		} catch (Exception e) {
			throw new Exception("ERROR! Properly Shut Down Application Failed!");
		}
		dailyTask.stopTask();
	}

}
