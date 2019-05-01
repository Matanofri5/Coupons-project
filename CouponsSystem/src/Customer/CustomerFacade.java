package Customer;
import java.awt.Window.Type;
import java.util.Collection;
import java.util.Iterator;

import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import MyExceptions.CouponNotAvailableException;

/**
 * @author Linoy & Matan
 * @Description: Customer Facade- login for customers
 */
public class CustomerFacade implements CouponClientFacade {
	private CustomerDBDAO custDBDAO = new CustomerDBDAO();
	private Customer customer;
	private CouponDAO couponDAO;
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private Coupon coupon;

	/**
	 * @partial CTOR 
	 */
	public CustomerFacade(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * @Empty CTOR
	 */
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
	public void dropTable () throws Exception{
		custDBDAO.dropTable();
	}
// 
	
	/**
	 * this method check password and name of Customer, if true return Customer login.
	 * @param name
	 * @param password
	 * @param Type
	 */
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
		custDBDAO.customerPurchaseCoupon(custcoupon, this.customer);
		custcoupon.setAmount(custcoupon.getAmount()-1);
		couponDBDAO.updateCoupon(custcoupon);
		
	}
	public Collection<Coupon> getAllpurchasedCoupons() throws Exception{
		return custDBDAO.getAllCustomerCoupons(customerId);
	}
	
	public Collection<Coupon> getAllpurchasedCouponsByType(Type type) throws Exception{
		Collection<Coupon> allCouponsByType = custDBDAO.getAllCustomerCoupons(customerId);
		for(Iterator<Coupon> iterator = allCouponsByType.iterator(); iterator.hasNext();) {
			Coupon coupon = iterator.next();
			if(coupon.getType() != CouponType) {
				iterator.remove();
			}
		}
		return allCouponsByType;
	}
	
	public Collection<Coupon> getAllpurchasedCouponsByPrice(Double price) throws Exception{
		Collection<Coupon> allCouponsByPrice = custDBDAO.getAllCustomerCoupons(customerId);
		for(Iterator<Coupon> iterator = allCouponsByPrice.iterator(); iterator.hasNext();) {
		    Coupon coupon = iterator.next();
		    if (coupon.getPrice() > price){
		        iterator.remove();
		    }
		}
		return allCouponsByPrice;
	}
}