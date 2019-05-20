package CustomerCoupon;

import java.util.Set;

public class CustomerCouponFacade {

	private CustomerCouponDAO customerCouponDAO;
	private CustomerCoupon customerCoupon;

	public CustomerCouponFacade(CustomerCoupon customerCoupon, CustomerCouponDAO customerCouponDAO) throws Exception {
		this.customerCoupon = customerCoupon;
		this.customerCouponDAO = new CustomerCouponDBDAO();
	}

	public CustomerCouponFacade() {
	}

	public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		customerCouponDAO.insertCustomerCoupon(customerCoupon);
	}

	public void removeCustomerCoupon(long customerId, long couponId) throws Exception {
		customerCouponDAO.removeCustomerCoupon(customerId, couponId);
	}

	public void updateCustomerCoupon(CustomerCoupon customerCoupon, long newcustomerId, long newcouponId)
			throws Exception {
		customerCoupon.setCustomerId(newcustomerId);
		customerCoupon.setCouponId(newcouponId);

		customerCouponDAO.updateCustomerCoupon(customerCoupon);
	}

	public CustomerCoupon getCustomerCoupon() throws Exception {
		return customerCoupon;
	}

	public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception {
		return customerCouponDAO.getAllCustomerCoupon();
	}

	public void dropTable() throws Exception {
		customerCouponDAO.dropTable();
	}
}
