package MyExceptions;

import Company.Company;

public class CompanyCreationException extends Exception {

	private Company company;

	public CompanyCreationException(Company company) {
		this.company = company;
	}

	@Override
	public String getMessage() {
		return "Faild to create " + this.company.getCompanyName() + " company";
	}

}
