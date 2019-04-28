package MyExceptions;

import Coupon.Coupon;

public class RemoveCouponException extends Exception {

	private Coupon coupon;
	
	public RemoveCouponException (String msg) {
		super(msg);
	}
}
