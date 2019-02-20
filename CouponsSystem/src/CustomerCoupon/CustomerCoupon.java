package CustomerCoupon;

public class CustomerCoupon {

	private long customerId;
	private long couponId;
	
	public CustomerCoupon() {
		super();
	}

	public CustomerCoupon(long customerId, long couponId) {
		super();
		this.customerId = customerId;
		this.couponId = couponId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "CustomerCoupon [customerId=" + customerId + ", couponId=" + couponId + "]";
	}
	
	
	
}
