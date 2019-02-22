package Coupon;

import java.sql.Date;

public class Coupon {

	private long id;
	private String title;
	private Date startDate; 
	private Date endDate;
	private int amount;
	private String message;
	private double price;
	private String image;
	private CouponType type;
	
	public Coupon() {
	}

	public Coupon(long id, String title, java.util.Date startDate, java.util.Date endDate, int amount, String message, double price,
			String image, CouponType couponType) {
		this.id = id;
		this.title = title;
		this.startDate = (Date) startDate;
		this.endDate = (Date) endDate;
		this.amount = amount;
		this.message = message;
		this.price = price;
		this.image = image;
		this.type = couponType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date newStartDate) {
		startDate = (Date) newStartDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date newEndDate) {
		this.endDate = (Date) newEndDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Coupon--->  Id:" + id + ", Title:" + title + ", StartDate:" + startDate + ", EndDate:" + endDate
				+ ", Amount:" + amount + ", Message:" + message + ", Price:" + price + ", Image:" + image + ", Type:"
				+ type;
	}
}
