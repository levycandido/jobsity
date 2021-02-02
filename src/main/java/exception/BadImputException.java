package exception;

public class BadImputException extends RuntimeException {
	
	public BadImputException(String errorMessage) {
		super(errorMessage);
	}
}
