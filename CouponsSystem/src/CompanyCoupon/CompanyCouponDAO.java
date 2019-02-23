package CompanyCoupon;

import java.util.Set;

public interface CompanyCouponDAO {

	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception;

	public void removeCompanyCoupon(CompanyCoupon companyCoupon) throws Exception;

	public void updateCompanyCoupon(CompanyCoupon companyCoupon) throws Exception;

	public CompanyCoupon getCompanyCoupon(long id) throws Exception;

	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception;

	public void dropTable() throws Exception;
}
