package Customer;
import java.awt.Window.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Clients.ClientType;
import Clients.CouponClientFacade;
import Company.Company;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Coupon.DateUtils;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;
import MyExceptions.CompanyAlreadyExists;
import MyExceptions.CouponNotAvailableException;

/**
 * @author Linoy & Matan
 * @Description: Customer Facade- login for customers
 */
public class CustomerFacade implements CouponClientFacade {
	private CustomerDAO customerDAO = new CustomerDBDAO();
	private Customer customer = new Customer();
	private CouponDAO couponDAO = new CouponDBDAO();
	private Coupon coupon;
	private CustomerCouponDAO customerCouponDAO = new CustomerCouponDBDAO();

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
	public void removeCustomer(Customer customer) throws Exception {
		customerDAO.removeCustomer(customer);
	}
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
		customerDAO.dropTable();
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
	
	public void purchaseCoupon(Customer customer, long couponId) throws Exception {
		Coupon custcoupon = couponDAO.getCoupon(couponId);
		customer = customerDAO.getCustomer(customer.getId());
		
		if (custcoupon == null) {
			throw new CouponNotAvailableException("This coupon doesn't exist, customer failed purchase coupon");
		}
		if (custcoupon.getAmount() <= 0) {
			throw new CouponNotAvailableException("This coupon doesn't exist, customer failed purchase coupon");
		}	
		if (custcoupon.getEndDate().getTime() <= DateUtils.getCurrentDate().getTime()) {
			throw new CouponNotAvailableException("This coupon is out of stock !");
		}
		
		Set<Coupon> coupons = customerDAO.getAllCustomerCoupons(customer.getId());
		Iterator<Coupon> iterator = coupons.iterator();
		while (iterator.hasNext()) {
			Coupon current = iterator.next();
			if (current.getId()==couponId) {
				throw new CouponNotAvailableException("This coupon cannot be purchased again");
			}
		}
		
		customerDAO.customerPurchaseCoupon(custcoupon, customer);
		custcoupon.setAmount(custcoupon.getAmount()-1);
		couponDAO.updateCoupon(custcoupon);
	}
	
	
	public Collection<Coupon> getAllpurchasedCoupons(long customerId) throws Exception{
		return customerDAO.getAllCustomerCoupons(customerId);
	}
	
	public Set<Coupon> getAllCouponsByType (CouponType couponType) throws Exception{
		return couponDAO.getAllCouponsByType(couponType);
	}
	
	public Collection<Coupon> getAllpurchasedCouponsByType(Type type) throws Exception{
		Collection<Coupon> allCouponsByType = customerDAO.getAllCustomerCoupons(customerId);
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