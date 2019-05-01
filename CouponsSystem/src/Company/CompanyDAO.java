package Company;

import java.util.Set;

import Coupon.Coupon;

/**
 * @Author - Linoy & Matan
 * @Description:
 * interface class, has all the functions that Company use in: company table
 */
public interface CompanyDAO {

	public void insertCompany(Company company) throws Exception;

	public void removeCompany(long id) throws Exception;

	public void updateCompany(Company company) throws Exception;

	public Company getCompany(long id) throws Exception;

	public Set<Company> getAllCompanys() throws Exception;
	
	public Set<Coupon> getAllCompanyCoupons(long companyId) throws Exception;
	
	public boolean login (String companyName, String password) throws Exception;
	
	public void removeCouponFromCompany(long couponId, long id) throws Exception;
	
//	public void addCoupon(Coupon coupon) throws Exception;
	
	public void dropTable() throws Exception;

}