package Coupon;
import java.util.Set;

public interface CouponDAO {
	public void insertCoupon(Coupon coupon) throws Exception;

	public void removeCoupon(long id) throws Exception;

	public void updateCoupon(Coupon coupon) throws Exception;

	public Coupon getCoupon(long id) throws Exception;

	public Set<Coupon> getAllCoupon() throws Exception;
    
	public void dropTable() throws Exception;
}


