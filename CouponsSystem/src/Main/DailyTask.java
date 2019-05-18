package Main;

import java.util.Date;
import java.util.Set;

import Company.CompanyDAO;
import Company.CompanyDBDAO;
import CompanyCoupon.CompanyCouponDAO;
import CompanyCoupon.CompanyCouponDBDAO;
import Coupon.Coupon;
import Coupon.CouponDAO;
import Coupon.CouponDBDAO;
import Coupon.DateUtils;
import CustomerCoupon.CustomerCouponDAO;
import CustomerCoupon.CustomerCouponDBDAO;

public class DailyTask implements Runnable {

	public boolean exit = false;
	private Date localDate;
	private long id;
	private boolean running = true;
	private CouponDAO couponDAO = new CouponDBDAO();
	private CompanyDAO companyDAO = new CompanyDBDAO();
//	private CompanyCouponDAO companyCouponDAO = new CompanyCouponDBDAO();
//	private CustomerCouponDAO customerCouponDAO = new CustomerCouponDBDAO();
	
	public DailyTask() {
	}

	
	public void run () {
		while (running) {
			
			try {
				System.out.println("Daily task starting now.....");
				Set<Coupon> allCoupons = couponDAO.getAllCoupons();
				for (Coupon coupon : allCoupons) {
					if (coupon.getEndDate().before(DateUtils.getCurrentDate())) {
						companyDAO.removeCouponFromCompany(coupon.getId());
						couponDAO.removeCoupon(coupon);
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