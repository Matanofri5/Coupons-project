package MyExceptions;

import Company.Company;

/**
 * @CompanyAlreadyExistsException if The name of the company already exists
 */
public class CompanyAlreadyExistsException extends Exception {

	private Company company;
	
	public CompanyAlreadyExistsException (String msg) {
		super(msg);
	}
}
