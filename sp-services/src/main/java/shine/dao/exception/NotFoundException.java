package shine.dao.exception;

import com.shine.ShineError;

public class NotFoundException extends ShineException {

	public NotFoundException(ShineError error) {
		super(error);
	}

}
