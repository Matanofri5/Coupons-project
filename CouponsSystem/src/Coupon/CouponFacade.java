package Coupon;
import java.util.Date;
import java.util.Set;

public class CouponFacade {
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private Coupon coupon;

	public CouponFacade(Coupon coupon) {
		this.coupon= coupon;
	}

	public CouponFacade() {
	}

	public void insertCoupon(Coupon coupon) throws Exception {
		couponDBDAO.insertCoupon(coupon);
	}

	public void removeCoupon(long id) throws Exception {
		couponDBDAO.removeCoupon(id);
	}

////<<<<<<< HEAD
//	public void updateCoupon(Coupon coupon, long newId, String newTitle, Date newStartDate, Date newEndDate, int newAmount, 
//			String newMessage, double newPrice, String newImage, CouponType newcouponType) throws Exception {
//		coupon.setId(newId);
//=======
	public void updateCoupon(Coupon coupon, long whatid, String newTitle, Date newStartDate, Date newEndDate, int newAmount, 
			String newMessage, double newPrice, String newImage, CouponType newcouponType) throws Exception {
		coupon.setId(whatid);
//>>>>>>> 02e87805fa802739312164aa300547aecce1d34f
		coupon.setTitle(newTitle);
		coupon.setStartDate(newStartDate);
		coupon.setEndDate(newEndDate);
		coupon.setAmount(newAmount);
		coupon.setMessage(newMessage);
		coupon.setPrice(newPrice);
		coupon.setImage(newImage);
		coupon.setType(newcouponType);
		
		couponDBDAO.updateCoupon(coupon);
	}

	public Coupon getCoupon(long id) throws Exception {
		return couponDBDAO.getCoupon(id);
	}

	public Set<Coupon> getAllCoupon() throws Exception {
//		 CouponDBDAO copDAO=new CouponDBDAO();
		return couponDBDAO.getAllCoupon();
	}

	public void dropTable () throws Exception{
		couponDBDAO.dropTable();
	}
}