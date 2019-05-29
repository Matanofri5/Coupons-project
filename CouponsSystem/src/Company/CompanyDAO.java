package Company;

import java.util.List;
import java.util.Set;

import CompanyCoupon.CompanyCoupon;
import Coupon.Coupon;

/**
 * @Author - Linoy & Matan
 * @Description:
 * interface class, has all the functions that Company use in: company table
 */
public interface CompanyDAO {

	public void insertCompany(Company company) throws Exception;
	
	public void companyCreateCoupon(Company company, Coupon coupon) throws Exception;

	public void removeCompany(Company company) throws Exception;

	public void updateCompany(Company company) throws Exception;

	public Company getCompany(long id) throws Exception;

	public Set<Company> getAllCompanys() throws Exception;
	
	public List<Long> getAllCompanyCoupons(long companyId) throws Exception;
	
	public Set<Coupon> getCompanyCoupons(long companyId) throws Exception;
	
	public void removeCouponFromCompany(long couponId) throws Exception;
	
	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception;
	
	public List<Long> getCouponId (long companyId) throws Exception;
	
	public boolean login (String companyName, String password) throws Exception;

	public void dropTable() throws Exception;




	
}