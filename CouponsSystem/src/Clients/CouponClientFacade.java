package Clients;

/**
 * @author Linoy & Matan
 * @Description : interface class for logins to Admin, Company and Customer.
 */
public interface CouponClientFacade {

	public CouponClientFacade login (String name, String password, ClientType clientType)throws Exception ;
	
}
