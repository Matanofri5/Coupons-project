package MyExceptions;

/**
 * @LoginException if The login to the system failed :(
 */
public class LoginException extends Exception{
	
	private LoginException loginException;
	
	public LoginException (String msg) {
		super(msg);
	}
}
