package in.taskoo.core.exception;

import java.util.Map;


/*
 * HTTP status 400
 */
public class InvalidDataException extends ApplicationException {

	private static final long serialVersionUID = 6963814105590497719L;

	public InvalidDataException(final Errors errors) {
		super(errors);
	}
	
	public InvalidDataException(final Error error) {
		super(error);
	}
	
	public static InvalidDataException getException(ValidationMessage validationMessage, Map<String, String> placeholders) {
		final Errors errors = new Errors();
		errors.addError(validationMessage, placeholders);
		return new InvalidDataException(errors);
	}
	
	public static InvalidDataException getException(ValidationMessage validationMessage) {
		final Errors errors = new Errors();
		errors.addError(validationMessage);
		return new InvalidDataException(errors);
	}


	public static void throwException(final ValidationMessage validationMessage, final Map<String, String> placeholders) {
		throw getException(validationMessage,placeholders);
	}

	public static void throwException(final ValidationMessage validationMessage) {
		throw getException(validationMessage);
	}

}