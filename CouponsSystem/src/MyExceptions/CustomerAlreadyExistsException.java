package MyExceptions;

import Customer.Customer;

/**
 * @CustomerAlreadyExistsException if The name of the customer already exists
 */
public class CustomerAlreadyExistsException extends Exception{
	
	private Customer customer;
	
	public CustomerAlreadyExistsException (String msg) {
		super(msg);
	}

}
