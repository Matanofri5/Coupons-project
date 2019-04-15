package Customer;

import java.util.Set;

import Coupon.Coupon;

public interface CustomerDAO {
	public void insertCustomer(Customer Customer) throws Exception;

	public void removeCustomer(long id) throws Exception;

	public void updateCustomer(Customer Customer) throws Exception;

	public Customer getCustomer(long id) throws Exception;

	public Set<Customer> getAllCustomer() throws Exception;
	
	public void customerPurchaseCoupon (Coupon coupon, Customer customer) throws Exception;

	public void dropTable() throws Exception;

}