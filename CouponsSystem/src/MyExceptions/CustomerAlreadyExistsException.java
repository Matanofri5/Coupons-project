package MyExceptions;

import Customer.Customer;

public class CustomerAlreadyExistsException extends Exception{
	
	private Customer customer;
	
	public CustomerAlreadyExistsException (String msg) {
		super(msg);
	}

}
