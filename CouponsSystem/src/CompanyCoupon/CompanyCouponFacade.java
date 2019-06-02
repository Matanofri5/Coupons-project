package CompanyCoupon;

import java.util.Set;

public class CompanyCouponFacade {
	
	private CompanyCouponDAO companyCouponDAO;
	private CompanyCoupon companyCoupon;

	public CompanyCouponFacade(CompanyCoupon companyCoupon, CompanyCouponDAO companyCouponDAO) throws Exception {
		this.companyCoupon = companyCoupon;
		this.companyCouponDAO = new CompanyCouponDBDAO();
	}

	public CompanyCouponFacade() throws Exception {
		this.companyCoupon = companyCoupon;
		this.companyCouponDAO = new CompanyCouponDBDAO();
	}

	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		companyCouponDAO.insertCompanyCoupon(companyCoupon);
	}

	public void removeCompanyCoupon(long companyId, long couponId) throws Exception {
		companyCouponDAO.removeCompanyCoupon(companyId, couponId);
	}

	public void updateCompanyCoupon(CompanyCoupon companyCoupon, long newcompanyId, long newcouponId) throws Exception {
		companyCoupon.setCompanyId(newcompanyId);
		companyCoupon.setCouponId(newcouponId);

		companyCouponDAO.updateCompanyCoupon(companyCoupon);
	}

	public CompanyCoupon getCompanyCoupon() throws Exception {
		return companyCoupon;
	}

	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception {
		return companyCouponDAO.getAllCompanyCoupon();
	}
}
