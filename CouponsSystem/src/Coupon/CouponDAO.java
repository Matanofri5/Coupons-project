package Coupon;

import java.util.Set;

/**
 * @Author - Linoy & Matan
 * @Description:
 * interface class, has all the functions that Coupon use in: Coupon table
 */
public interface CouponDAO {
	
	public void insertCoupon(Coupon coupon) throws Exception;

	public void removeCoupon(Coupon coupon) throws Exception;

	public void updateCoupon(Coupon coupon) throws Exception;

	public Coupon getCoupon(long id) throws Exception;

	public Set<Coupon> getAllCoupons() throws Exception;
	
	public Set<Coupon> getAllCouponsByType (CouponType couponType) throws Exception;

}
