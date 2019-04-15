package MyExceptions;

import Coupon.Coupon;

public class CouponNotAvailableException extends Exception {
	
	private Coupon coupon;
	
	public CouponNotAvailableException (String msg) {
		super (msg);
	}
}
