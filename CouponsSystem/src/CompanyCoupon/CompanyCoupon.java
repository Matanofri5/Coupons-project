package CompanyCoupon;

public class CompanyCoupon {

	private long CompanyId;
	private long CouponId;
	
	public CompanyCoupon() {
		super();
	}

	public CompanyCoupon(long companyId, long couponId) {
		super();
		CompanyId = companyId;
		CouponId = couponId;
	}

	public long getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(long companyId) {
		CompanyId = companyId;
	}

	public long getCouponId() {
		return CouponId;
	}

	public void setCouponId(long couponId) {
		CouponId = couponId;
	}

	@Override
	public String toString() {
		return "CompanyCoupon [CompanyId=" + CompanyId + ", CouponId=" + CouponId + "]";
	}
	
	
}
