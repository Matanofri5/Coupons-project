package CustomerCoupon;

import java.util.Collection;
import java.util.Set;

/**
 * @Author - Linoy & Matan
 * @Description:
 * interface class, has all the functions that CustomerCoupon use in: CustomerCoupon table
 */
public interface CustomerCouponDAO {

	public void insertCustomerCoupon(CustomerCoupon customerCoupon) throws Exception;

	public void removeCustomerCoupon(long customerId, long couponId) throws Exception;
	
	public void updateCustomerCoupon(CustomerCoupon customerCoupon) throws Exception;

	public CustomerCoupon getCustomerCoupon(long id) throws Exception;

	public Set<CustomerCoupon> getAllCustomerCoupon() throws Exception;
	
	public Collection<Long> getAllCouponsId(long customerId) throws Exception;

}
