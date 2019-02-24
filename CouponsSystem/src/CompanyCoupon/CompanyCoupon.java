package CompanyCoupon;

public class CompanyCoupon {

	private long companyId;
	private long couponId;

	public CompanyCoupon() {
	}

	public CompanyCoupon(long companyId, long couponId) {
		this.companyId = companyId;
		this.couponId = couponId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "CompanyCoupon---> Company Id:" + companyId + ", Coupon Id:" + couponId;
	}

}
