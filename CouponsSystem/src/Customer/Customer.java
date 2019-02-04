package Customer;

public class Customer {

	
	private long Id;
	private String Cust_name;
	private String Password;
	
	public Customer() {
	}

	public Customer(long id, String cust_name, String password) {
		this.Id = id;
		this.Cust_name = cust_name;
		this.Password = password;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getCust_name() {
		return Cust_name;
	}

	public void setCust_name(String cust_name) {
		Cust_name = cust_name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "Customer [Id=" + Id + ", Cust_name=" + Cust_name + ", Password=" + Password + "]";
	}

    
	

	
    
}
