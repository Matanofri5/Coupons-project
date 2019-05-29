package CompanyCoupon;

import java.util.Set;

/**
 * @Author - Linoy & Matan
 * @Description:
 * interface class, has all the functions that CompanyCoupon use in: CompanyCoupon table
 */
public interface CompanyCouponDAO {

	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception;

	public void removeCompanyCoupon(long companyId, long couponId) throws Exception;

	public void updateCompanyCoupon(CompanyCoupon companyCoupon) throws Exception;

	public CompanyCoupon getCompanyCoupon(long id) throws Exception;

	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception;
}
