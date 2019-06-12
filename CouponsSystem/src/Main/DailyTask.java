package Main;

import java.util.Set;
import Company.CompanyDAO;
import Company.CompanyDBDAO;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.DateUtils;

public class DailyTask implements Runnable {

	public boolean exit = false;
	private boolean running = true;
	private static int SLEEPTIME = 24 * 1000 * 3600;
	private CouponDAO couponDAO = new CouponDBDAO();
	private CompanyDAO companyDAO = new CompanyDBDAO();
	private int sleepingTime = DailyTask.SLEEPTIME;
	
	public DailyTask (int sleepTime) {
		DailyTask.SLEEPTIME = sleepTime;
		this.sleepingTime = sleepTime;
	}
	

	public void run () {
		while (running) {
			
			try {
				Thread.sleep(5000);
				// Thread.sleep(1000 * 60 * 60 * 24);
				
				Set<Coupon> allCoupons = couponDAO.getAllCoupons();
				for (Coupon coupon : allCoupons) {
					
					if (coupon.getEndDate().equals(DateUtils.getCurrentDate()) || coupon.getEndDate().before(DateUtils.getCurrentDate())) {
						companyDAO.removeCouponFromCompany(coupon.getId());
//						couponDAO.removeCoupon(coupon);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
//	public void couponExpired() throws Exception {
//
//		Set<Coupon> coupons = couponDAO.getAllCoupons();
//		for (Coupon coupon : coupons) {
//			if (coupon.getEndDate().before(DateUtils.getCurrentDate())) {
//				id = coupon.getId();
//			couponDAO.removeCoupon(coupon);
//			System.out.println("coupon " +couponDAO.getCoupon(id)+ " has been delete");
//			}
//			}
//		
//	}
//
//	public void startThread() throws Exception {
//		Thread t1 = new Thread(this);
//		t1.start();
//	}
//
//	// @Override
//	// public synchronized void run() {
//	// // TODO Auto-generated method stub
//	// while (!exit)
//	// try {
//	// this.couponExpired();
//	// Thread.sleep(1000 * 60 * 60 * 24);
//	// } catch (Exception e) {
//	// System.out.println(e.getMessage());
//	// }
//	//
//	// }
//
//	@Override
//	public synchronized void run() {
//		// TODO Auto-generated method stub
//		while (!exit)
//			try {
//				this.couponExpired();
//				Thread.sleep(5000);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//	}

	public void stopTask() {
		this.running = false;
	}
}