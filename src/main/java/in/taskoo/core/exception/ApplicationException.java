package in.taskoo.core.exception;

import java.util.List;
import java.util.Map;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 100672824296L;
    private final Errors errors;

    public ApplicationException(String message, final Throwable exception) {
        super(message, exception);
        this.errors = null;
    }

    public ApplicationException(String message) {
        super(message);
        this.errors = null;
    }

    public ApplicationException(Errors errors) {
        super(errors.toString());
        this.errors = errors;
    }
    
    public ApplicationException(Error error) {
    	super(error.toString());
    	this.errors = new Errors(error);
    }

    public static void throwException(final ValidationMessage validationMessage,final Map<String, String> placeholders) {
        final Errors errors = new Errors();
        errors.addError(validationMessage, placeholders);
        throw new ApplicationException(errors);
    }

    public static void throwException(final ValidationMessage validationMessage) {
        final Errors errors = new Errors();
        errors.addError(validationMessage, null);
        throw new ApplicationException(errors);
    }

    public Errors getErrors() {
        return errors;
    }

    public List<Error> getErrorList() {
        return errors == null ? null : errors.getErrors();
    }

}
