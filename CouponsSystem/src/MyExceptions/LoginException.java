package MyExceptions;

public class LoginException extends Exception{
	
	private LoginException loginException;
	
	public LoginException (String msg) {
		super(msg);
	}
}
