package MyExceptions;

import Company.Company;

public class CompanyAlreadyExists extends Exception {

	private Company company;
	
	public CompanyAlreadyExists (Company company) {
		this.company = company;
	}
	
	public String getMessage() {
		return "this company name is already exist";
	}
}
