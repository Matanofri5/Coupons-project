package CustomerCoupon;

import java.util.Set;

public class CustomerCouponFacade {

	private CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
	private CustomerCoupon customerCoupon;

	public CustomerCouponFacade(CustomerCoupon customerCoupon) {
		this.customerCoupon = customerCoupon;
	}

	public CustomerCouponFacade() {
	}

	public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		customerCouponDBDAO.insertCustomerCoupon(customerCoupon);
	}

	public void removeCustomerCoupon(CustomerCoupon customerCoupon) throws Exception {
		customerCouponDBDAO.removeCustomerCoupon(customerCoupon);
	}

	public void updateCustomerCoupon(CustomerCoupon customerCoupon, long newcustomerId, long newcouponId)
			throws Exception {
		customerCoupon.setCustomerId(newcustomerId);
		customerCoupon.setCouponId(newcouponId);

		customerCouponDBDAO.updateCustomerCoupon(customerCoupon);
	}

	public CustomerCoupon getCustomerCoupon() throws Exception {
		return customerCoupon;
	}

	public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception {
		// CustomerCouponDBDAO customerCouponDAO=new CustomerCouponDBDAO();
		return customerCouponDBDAO.getAllCustomerCoupon();
	}

	public void dropTable() throws Exception {
		customerCouponDBDAO.dropTable();
	}
}
