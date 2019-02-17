package CustomerCoupon;

public class CustomerCoupon {

	private long CustomerId;
	private long CouponId;
	
	public CustomerCoupon() {
		super();
	}

	public CustomerCoupon(long customerId, long couponId) {
		super();
		CustomerId = customerId;
		CouponId = couponId;
	}

	public long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(long customerId) {
		CustomerId = customerId;
	}

	public long getCouponId() {
		return CouponId;
	}

	public void setCouponId(long couponId) {
		CouponId = couponId;
	}

	@Override
	public String toString() {
		return "CustomerCoupon [CustomerId=" + CustomerId + ", CouponId=" + CouponId + "]";
	}
	
	
	
}
