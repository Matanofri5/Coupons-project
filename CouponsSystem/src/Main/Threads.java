
package Main;

import java.util.Date;
import java.util.Set;
import Coupon.Coupon;
import Coupon.CouponDBDAO;

public class Threads implements Runnable {

	private boolean exit = false;
	private Date localDate;
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private long id;

	public void couponExpired() throws Exception {
		Set<Coupon> coupons = couponDBDAO.getAllCoupon();
		for (Coupon coupon : coupons) {
			if (coupon.getEndDate().before(localDate))
				id = coupon.getId();
			couponDBDAO.removeCoupon(id);
			// long id= coupon.getId();
		}
	}

	@Override
	public synchronized void run() {
		while (!exit)
			try {
				this.couponExpired();
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

	}

}
