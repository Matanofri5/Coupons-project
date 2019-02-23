package CompanyCoupon;

import java.util.Set;

public class CompanyCouponFacade {
	private CompanyCouponDBDAO companyCouponDBDAO = new CompanyCouponDBDAO();
	private CompanyCoupon companyCoupon;

	public CompanyCouponFacade(CompanyCoupon companyCoupon) {
		this.companyCoupon = companyCoupon;
	}

	public CompanyCouponFacade() {
	}

	public void insertCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		companyCouponDBDAO.insertCompanyCoupon(companyCoupon);
	}

	public void removeCompanyCoupon(CompanyCoupon companyCoupon) throws Exception {
		companyCouponDBDAO.removeCompanyCoupon(companyCoupon);
	}

	public void updateCompanyCoupon(CompanyCoupon companyCoupon, long newcompanyId, long newcouponId ) throws Exception {
		CompanyCoupon.setCompanyId(newcompanyId);
		CompanyCoupon.setCouponId(newcouponId);
		
		CompanyCouponDBDAO.updateCompanyCoupon(companyCoupon);
	}

	public CompanyCoupon getCompanyCoupon() throws Exception {
		return companyCoupon;
	}

	public Set<CompanyCoupon> getAllCompanyCoupon() throws Exception {
//		 CompanyCouponDBDAO companyCouponDAO=new CompanyCouponDBDAO();
		return companyCouponDBDAO.getAllCompanyCoupon();
	}

	public void dropTable () throws Exception{
		companyCouponDBDAO.dropTable();
	}
}
