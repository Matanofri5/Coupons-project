package MyExceptions;

import Company.Company;

public class CompanyCreationFailedException extends Exception{

	private Company company;
	
	public CompanyCreationFailedException (Company company) {
		this.company = company;
	}
		public String getMessage() {
			return "Faild to create " + company.getCompanyName() + " company";    
		}
		
		
	}
	
