package demo.lims;


/**
 * @author pnatar
 * Adaptive Biotech Exception to be used for custom code.  
 * Why not checked exception: "The price of checked exceptions is an Open/Closed Principle violation. If you throw a checked exception from a method in your code and the catch is three levels above, you must declare that exception in the signature of each method between you and the catch. This means that a change at a low level of the software can force signature changes on many higher levels." Robert C. Martin, Clean Code, page 107
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String hint = "";// for resolving an error.
	//	public AbException() {
//
//	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
	public AppException(String message, Throwable cause,String hint ) {
		super(message, cause);
		setHint(hint);
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}
