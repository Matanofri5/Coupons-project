package MyExceptions;


/**
 * @ErrorConnectingDriverException if The connection to driver failed :(
 */
public class ErrorConnectingDriverException extends Exception {
	
    private ErrorConnectingDriverException errorConnectingDriverException;
    
    public ErrorConnectingDriverException (String msg) {
    	super(msg);
    }
	
	
}
