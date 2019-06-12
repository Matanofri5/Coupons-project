package Clients;

/**
 * @author Linoy & Matan
 * @Description : interface class for logins to Admin, Company and Customer.
 */
public interface CouponClientFacade {

	ClientType clientType = null;
	public CouponClientFacade login (String name, String password, ClientType clientType) ;
	
}
