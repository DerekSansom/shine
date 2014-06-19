package shine.dao.exception;

import com.shine.GeneralError;
import com.shine.ShineError;

public class ShineException extends Exception {

	private static final long serialVersionUID = -7704405752571846574L;
	private int code;

	public ShineException(String message) {
		super(message);
	}

	public ShineException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShineException(ShineError error) {
		super(error.getDescription());
		this.code = error.getCode();
	}

	public ShineException(Throwable cause) {
		super(cause);
		this.code = GeneralError.SYSTEM_ERROR.getCode();
	}

	public ShineException(ShineError error, String message) {
		super(message);
		this.code = error.getCode();
	}

	public ShineException(int code) {
		this.code = code;
	}

	public ShineException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ShineException(int code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
