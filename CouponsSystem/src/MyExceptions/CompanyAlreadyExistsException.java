package MyExceptions;

import Company.Company;

public class CompanyAlreadyExistsException extends Exception {

	private Company company;
	
	public CompanyAlreadyExistsException (String msg) {
		super(msg);
	}
}
