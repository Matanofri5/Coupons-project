package MyExceptions;

import Company.Company;

public class ErrorConnectingDriverException extends Exception {
	
    private ErrorConnectingDriverException errorConnectingDriverException;
    
    public ErrorConnectingDriverException (String msg) {
    	super(msg);
    }
	
	
}
