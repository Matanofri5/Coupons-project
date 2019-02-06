package Coupon;
import java.util.Date;
import java.util.Set;

public class CouponFacade {
	private CouponDBDAO coupDAO = new CouponDBDAO();
	private Coupon coupon;

	public CouponFacade(Coupon C) {
		this.coupon= C;
	}

	public CouponFacade() {
	}

	public void insertCoupon(Coupon coupon) throws Exception {
		coupDAO.insertCoupon(coupon);
	}

	public void removeCoupon(Coupon coupon) throws Exception {
		coupDAO.removeCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon, String newTitle, Date newStart_date, Date newEnd_date, Integer newAmount, 
			String newMessage, Double newPrice, String newImage, CouponType newcouponType) throws Exception {
		
		coupon.setTitle(newTitle);
		coupon.setStart_date(newStart_date);
		coupon.setEnd_date(newEnd_date);
		coupon.setAmount(newAmount);
		coupon.setMessage(newMessage);
		coupon.setPrice(newPrice);
		coupon.setImage(newImage);
		coupon.setType(newcouponType);
		
		
		coupDAO.updateCoupon(coupon);
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public Set<Coupon> getAllCoupon() throws Exception {
//		 CouponDBDAO copDAO=new CouponDBDAO();
		return coupDAO.getAllCoupon();
	}

	public void dropTable () throws Exception{
		coupDAO.dropTable();
	}
}