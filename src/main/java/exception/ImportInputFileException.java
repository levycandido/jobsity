package exception;

import java.io.IOException;

public class ImportInputFileException extends RuntimeException {

	public ImportInputFileException(String errorMessage) {
		super(errorMessage);
	}

}
