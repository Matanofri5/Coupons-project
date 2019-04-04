package Company;

import java.util.Set;

import Coupon.Coupon;

public interface CompanyDAO {

	public void insertCompany(Company company) throws Exception;

	public void removeCompany(long id) throws Exception;

	public void updateCompany(Company company) throws Exception;

	public Company getCompany(long id) throws Exception;

	public Set<Company> getAllCompanys() throws Exception;
	
	public Set<Coupon> getCoupons(long companyId) throws Exception;
	
	public void dropTable() throws Exception;

}