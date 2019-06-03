package Main;

import Clients.AdminFacade;
import Clients.ClientType;

public class TheTest {

	public static void main(String[] args) throws Exception {
		
		String name;
		String password;

		CouponSystem couponSystem = CouponSystem.getInstance();
		
		name = "admin";
		password = "1234";
		try {
			AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "12364", ClientType.ADMIN);
			System.err.println("login success!");
		} catch (Exception e) {
			throw new Exception("login faild");
		}
	}

}
