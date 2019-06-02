package Coupon;

import java.util.Date;
import java.util.Set;

public class CouponFacade {
	private CouponDAO couponDAO ;
	private Coupon coupon;

	public CouponFacade(Coupon coupon, CouponDAO couponDAO) throws Exception {
		this.coupon = coupon;
		this.couponDAO = new CouponDBDAO();
	}

	public CouponFacade() {
		this.coupon = coupon;
		this.couponDAO = new CouponDBDAO();
	}

	public void insertCoupon(Coupon coupon) throws Exception {
		couponDAO.insertCoupon(coupon);
	}

	public void removeCoupon(Coupon coupon) throws Exception {
		couponDAO.removeCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon, long whatid, Date newEndDate, double newPrice)
			throws Exception {
		coupon.setId(whatid);
		coupon.setEndDate(newEndDate);
		coupon.setPrice(newPrice);

		couponDAO.updateCoupon(coupon);
	}

	
	public Coupon getCoupon(long id) throws Exception {
		return couponDAO.getCoupon(id);
	}

	public Set<Coupon> getAllCoupon() throws Exception {
		return couponDAO.getAllCoupons();
	}
}