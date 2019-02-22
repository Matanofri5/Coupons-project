package CustomerCoupon;

import java.util.Set;

public interface CustomerCouponDAO {

		
		public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception;

		public void removeCustomerCoupon(CustomerCoupon customerCoupon) throws Exception;

		public void updateCustomerCoupon(CustomerCoupon customerCoupon) throws Exception;

		public CustomerCoupon getCustomerCoupon(long id) throws Exception;

		public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception;

		public void dropTable() throws Exception;

	}
