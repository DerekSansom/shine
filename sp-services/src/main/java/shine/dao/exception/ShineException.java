package shine.dao.exception;

import com.shine.GeneralError;
import com.shine.ShineError;

public class ShineException extends Exception {

	private static final long serialVersionUID = -7704405752571846574L;
	private int code;
	private ShineError error;

	public ShineException(String message) {
		super(message);
	}

	public ShineException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShineException(ShineError error) {
		super(error.getDescription());
		this.code = error.getCode();
		this.error = error;
	}

	public ShineException(Throwable cause) {
		super(cause);
		this.code = GeneralError.SYSTEM_ERROR.getCode();
	}

	public ShineException(ShineError error, String message) {
		super(message);
		this.code = error.getCode();
	}

	/**
	 * Use ErrorCode
	 * 
	 * @param code
	 */
	@Deprecated
	public ShineException(int code) {
		this.code = code;
	}

	/**
	 * Use ErrorCode
	 * 
	 * @param code
	 */
	@Deprecated
	public ShineException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Use ErrorCode
	 * 
	 * @param code
	 */
	@Deprecated
	public ShineException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public ShineError getError() {
		return error;
	}

}
