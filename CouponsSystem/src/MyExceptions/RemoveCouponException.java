package MyExceptions;

import Coupon.Coupon;

/**
 * @RemoveCouponException If deleting a coupon failed :(
 */
public class RemoveCouponException extends Exception {

	private Coupon coupon;
	
	public RemoveCouponException (String msg) {
		super(msg);
	}
}
