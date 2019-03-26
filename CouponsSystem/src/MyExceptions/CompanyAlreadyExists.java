package MyExceptions;

import Company.Company;

public class CompanyAlreadyExists extends Exception {

	private Company company;
	
	public CompanyAlreadyExists (String msg) {
		super(msg);
	}
	
	public String getMessage() {
		return "this company name is already exist";
	}
}
