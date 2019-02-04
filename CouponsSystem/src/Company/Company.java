package Company;

public class Company {

	
	private long Id;
	private String Comp_name;
	private String Password;
	private String Email;
	
	public Company() {
	}

	public Company(long id, String comp_name, String password,String Email) {
		this.Id = id;
		this.Comp_name = comp_name;
		this.Password = password;
		this.Email = Email;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getComp_name() {
		return Comp_name;
	}

	public void setComp_name(String comp_name) {
		Comp_name = comp_name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "Company [Id=" + Id + ", Comp_name=" + Comp_name + ", Password=" + Password + ", Email=" + Email + "]";
	}
	




	
	

	
	
}
