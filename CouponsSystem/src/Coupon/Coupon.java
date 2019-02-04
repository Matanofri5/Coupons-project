package Coupon;
import java.util.Date;

public class Coupon {

	private long Id;
	private String Title;
	private Date Start_date;
	private Date End_date;
	private Integer Amount;
	private String Message;
	private Double Price;
	private String Image;
	private CouponType type;
	
	public Coupon() {
	}

	public Coupon(long id, String title, Date start_date, Date end_date, Integer amount, String message, Double price,
			String image, CouponType type) {
		this.Id = id;
		this.Title = title;
		this.Start_date = start_date;
		this.End_date = end_date;
		this.Amount = amount;
		this.Message = message;
		this.Price = price;
		this.Image = image;
		this.type=type;
		
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

	public Date getStart_date() {
		return Start_date;
	}

	public void setStart_date(Date start_date) {
		Start_date = start_date;
	}

	public Date getEnd_date() {
		return End_date;
	}

	public void setEnd_date(Date end_date) {
		End_date = end_date;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}
	
	

	@Override
	public String toString() {
		return "Coupon [Id=" + Id + ", Title=" + Title + ", Start_date=" + Start_date + ", End_date=" + End_date
				+ ", Amount=" + Amount + ", Message=" + Message + ", Price=" + Price + ", Image=" + Image + ", type="
				+ type + "]";
	}

	
	
	
	
}
