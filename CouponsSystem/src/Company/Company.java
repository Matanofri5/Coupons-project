package Company;

public class Company {

	
	private long id;
	private String companyName;
	private String password;
	private String email;
	
	public Company() {
	}

	public Company(long id, String companyName, String password,String email) {
		this.id = id;
		this.companyName = companyName;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company---> Id:" + id + ", CompanyName:" + companyName + ", password:" + password + ", email:" + email;
	}
	




	
	

	
	
}
