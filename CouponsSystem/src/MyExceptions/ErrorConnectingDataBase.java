package MyExceptions;

import Company.Company;

public class ErrorConnectingDataBase extends Exception {
	
    private ErrorConnectingDataBase errorConnectingDataBase;
    
    public ErrorConnectingDataBase (String msg) {
    	super(msg);
    }
	
	
}
