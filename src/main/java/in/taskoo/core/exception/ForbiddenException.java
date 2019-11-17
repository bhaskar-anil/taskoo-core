package in.taskoo.core.exception;

import java.util.Map;


/*
 * HTTP status 403
 */
public class ForbiddenException extends ApplicationException {

	private static final long serialVersionUID = -8177327492286835544L;

	public ForbiddenException(final Errors errors) {
		super(errors);
	}
	
	public ForbiddenException(final Error error) {
		super(error);
	}

	public static ForbiddenException getException(final ValidationMessage validationMessage, final Map<String, String> placeholders) {
		final Errors errors = new Errors();
		errors.addError(validationMessage, placeholders);
		return new ForbiddenException(errors);
	}

	public static ForbiddenException getException(final ValidationMessage validationMessage) {
		final Errors errors = new Errors();
		errors.addError(validationMessage);
		return new ForbiddenException(errors);
	}
	public static void throwException(final ValidationMessage validationMessage, final Map<String, String> placeholders) {
		throw getException(validationMessage,placeholders);
	}
	
	public static void throwException(final ValidationMessage validationMessage) {
		throw getException(validationMessage);
	}

}