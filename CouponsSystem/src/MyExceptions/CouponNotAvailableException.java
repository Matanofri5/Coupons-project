package MyExceptions;

import Coupon.Coupon;

/**
 * @CouponNotAvailableException if The Coupon Not Available
 */
public class CouponNotAvailableException extends Exception {
	
	private Coupon coupon;
	
	public CouponNotAvailableException (String msg) {
		super (msg);
	}
}
