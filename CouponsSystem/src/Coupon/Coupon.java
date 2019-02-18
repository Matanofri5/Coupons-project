package Coupon;

import java.sql.Date;

public class Coupon {

	private long Id;
	private String Title;
	private Date StartDate; 
	private Date EndDate;
	private int Amount;
	private String Message;
	private double Price;
	private String Image;
	private CouponType Type;
	
	public Coupon() {
		super();
	}

	public Coupon(long id, String title, java.util.Date date, java.util.Date date2, int amount, String message, double price,
			String image, CouponType couponType) {
		super();
		this.Id = id;
		this.Title = title;
		this.StartDate = (Date) date;
		this.EndDate = (Date) date2;
		this.Amount = amount;
		this.Message = message;
		this.Price = price;
		this.Image = image;
		this.Type = couponType;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(java.util.Date newStartDate) {
		StartDate = (Date) newStartDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(java.util.Date newEndDate) {
		this.EndDate = (Date) newEndDate;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public CouponType getType() {
		return Type;
	}

	public void setType(CouponType type) {
		this.Type = type;
	}

	@Override
	public String toString() {
		return "Coupon [Id=" + Id + ", Title=" + Title + ", StartDate=" + StartDate + ", EndDate=" + EndDate
				+ ", Amount=" + Amount + ", Message=" + Message + ", Price=" + Price + ", Image=" + Image + ", Type="
				+ Type + "]";
	}
	
	
	
	

}
