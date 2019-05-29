package Customer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import Clients.ClientType;
import Clients.CouponClientFacade;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.CouponType;
import Coupon.DateUtils;
import MyExceptions.CouponNotAvailableException;

/**
 * @author Linoy & Matan
 * @Description: Customer Facade- login for customers
 */
public class CustomerFacade implements CouponClientFacade {
	private CustomerDAO customerDAO ;
	private CouponDAO couponDAO ;
	
	/**
	 * @partial CTOR 
	 */
	public CustomerFacade(CustomerDAO customerDAO, CouponDAO couponDAO) throws Exception {
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
	}
	
	/**
	 * @throws Exception 
	 * @Empty CTOR
	 */
	public CustomerFacade() throws Exception {
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
	}
	
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
	
	/**
	 * This method purchase purchase coupon. by getting all coupons it checks if,
	 * 1.if null.
	 * 2.if coupon amount equal or smaller then 0.
	 * 3.if the coupon date is expired/out of stock.
	 * 4.if the new coupon id's same to exists coupon id.
	 * if all this conditions will happen the customer cannot purchase a new coupon.
	 * if the coupon not exists it create and update at the table and set amount by -1.
	 * @param customer
	 * @param couponId
	 * @throws Exception
	 */
	public void purchaseCoupon(Customer customer, long couponId) throws Exception {
		Coupon custcoupon = couponDAO.getCoupon(couponId);
		customer = customerDAO.getCustomer(customer.getId());
		
		if (custcoupon == null) {
			throw new CouponNotAvailableException("This coupon doesn't exist, customer failed purchase coupon");
		}
		if (custcoupon.getAmount() <= 0) {
			throw new CouponNotAvailableException("cannot buy coupon with 0 amount");
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
	
	
	public Set<Coupon> getAllpurchasedCoupons(long customerId) throws Exception{
		return customerDAO.getAllCustomerCoupons(customerId);
	}
	
	
	public List<Coupon> getAllCustomerCoupon(Customer customer) throws Exception {
		long customerId= customer.getId();
		List<Long> coupons = customerDAO.getCustomerCoupons(customerId);
		List<Coupon> allcoupons = new ArrayList<Coupon>();
		for (Long id : coupons) {
			allcoupons.add(couponDAO.getCoupon(id));
		}
		return allcoupons;
	}
	
	
	public List<Coupon> getCouponbyType(Customer customer, CouponType type) throws Exception{
		List<Coupon> coupons = getAllCustomerCoupon(customer);
		List<Coupon> couponByType = new ArrayList<Coupon>();
		try {
			for (Coupon coupon : coupons) {
				if (coupon.getType().equals(type)) {
					couponByType.add(coupon);
				}}}
		catch (Exception e) {
			System.out.println(e);
		}
		return couponByType;
	}
	
	
	public List<Coupon> getCouponByPrice (Customer customer, double price) throws Exception{
		List<Coupon> coupons = getAllCustomerCoupon(customer);
		List<Coupon> couponByPrice = new ArrayList<Coupon>();
		try {
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= price) {
					couponByPrice.add(coupon);
				}}}
		catch (Exception e) {
			System.out.println(e);
		}
		return couponByPrice;
	}
	

	
}