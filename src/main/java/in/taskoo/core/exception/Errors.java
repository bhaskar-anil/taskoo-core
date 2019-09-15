package in.taskoo.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Errors{

    private final List<Error> errors = new ArrayList<>();

	public Errors(Error error) {
		errors.add(error);
	}
	
	public Errors() {
		
	}
	
    public void addError(final ValidationMessage validationMessage, final Map<String, String> placeholders) {
        errors.add(new Error(validationMessage.getCode(), validationMessage.getPlaceholderMessage(), placeholders));
    }

    public void addError(final ValidationMessage validationMessage) {
        errors.add(new Error(validationMessage.getCode(),  validationMessage.getPlaceholderMessage(), null));
    }

    public void clear() {
        errors.clear();
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "Errors [errorMessages=" + errors + "]";
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
