package Customer;
import java.awt.Window.Type;
import java.util.Set;

import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import MyExceptions.CouponNotAvailableException;

public class CustomerFacade implements CouponClientFacade {
	private CustomerDBDAO custDAO = new CustomerDBDAO();
	private Customer customer;
	private CouponDAO couponDAO;
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private Coupon coupon;

	public CustomerFacade(Customer customer) {
		this.customer = customer;
	}
	
	  
	public CustomerFacade() {
	}

//	public void insertCustomer(Customer customer) throws Exception {
//		custDAO.insertCustomer(customer);
//	}
//
//	public void removeCustomer(long id) throws Exception {
//		custDAO.removeCustomer(id);
//	}
//
//	public void updateCustomer(Customer customer, long newid, String newcustomerName, String newpassword) throws Exception {
//		customer.setId(newid);
//		customer.setCustomerName(newcustomerName);
//		customer.setPassword(newpassword);
//		custDAO.updateCustomer(customer);
//	}
//
//	public Customer getCustomer(long id) throws Exception {
//		return custDAO.getCustomer(id);
//	}
//
//	public Set<Customer> getAllCustomer() throws Exception {
//		System.out.println(custDAO.getAllCustomer());
//		return custDAO.getAllCustomer();
//	}
//
//	public void dropTable () throws Exception{
//		custDAO.dropTable();
//	}
// 
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void purchaseCoupon(Coupon coupon) throws Exception {
		Coupon custcoupon = couponDBDAO.getCoupon(coupon.getId());
		
		if (custcoupon == null) {
			throw new CouponNotAvailableException("customer failed purchase coupon");
		}
		if (custcoupon.getAmount() > 0) {
			throw new CouponNotAvailableException("customer failed purchase coupon");
		}
		custDAO.customerPurchaseCoupon(custcoupon, this.customer);
		custcoupon.setAmount(custcoupon.getAmount()-1);
		couponDBDAO.updateCoupon(custcoupon);
		
	}
	public void getAllpurchasedCoupons() {
		
		
	}
	
	public void getAllpurchasedCouponsByType(Type type) {
		
	}
	
	public void getAllpurchasedCouponsByPrice(Double price) {
		
	}
	
	
}