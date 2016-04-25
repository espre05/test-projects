package ab.lims;

/**
 * More refined exceptions needed to take appropriate retry/fail actions.
 * 
 * FatalException to signify that the messge/process would need human interevention to correct, e.g if it is a mq-message, the message would go to dead-letter q. 
 * 
 * @author pnatar
 *
 */
public class AbFatalException extends AbException{
	private static final long serialVersionUID = 1L;

	public AbFatalException(String message, Throwable cause) {
		super(message, cause);
	}
	public AbFatalException(String message) {
		super(message);
	}
	public AbFatalException(Throwable cause) {
		super(cause);
	}
}
