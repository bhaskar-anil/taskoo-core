package in.taskoo.core.exceptionhandler;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.ApplicationException;
import in.taskoo.core.exception.Error;
import in.taskoo.core.exception.Errors;
import in.taskoo.core.exception.ForbiddenException;
import in.taskoo.core.exception.InvalidDataException;
import in.taskoo.core.exception.NoDataFoundException;
import in.taskoo.core.util.MapBuilder;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@Autowired(required = false)
	private HttpServletRequest request;

	@Resource
	private MessageSource messageSource;

	Logger LOG = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@ExceptionHandler({ Exception.class, ApplicationException.class })
	protected ResponseEntity<String> handleOtherExceptions(Exception exception) {
		LOG.debug(ExceptionUtils.getFullStackTrace(exception));
		return new ResponseEntity<>(ExceptionUtils.getFullStackTrace(exception), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvalidDataException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleInvalidDataException(
			InvalidDataException invalidDataException) {
		convertToLocale(invalidDataException.getErrorList());
		return new ResponseEntity<>(invalidDataException.getErrorList(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleNoDataFoundException(
			NoDataFoundException noDataFoundException) {
		convertToLocale(noDataFoundException.getErrorList());
		return new ResponseEntity<>(noDataFoundException.getErrorList(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleForbiddenException(
			ForbiddenException forbiddenException) {
		convertToLocale(forbiddenException.getErrorList());
		return new ResponseEntity<>(forbiddenException.getErrorList(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleMethodArgumentException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Errors errors = new Errors();
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.addError(ApplicationErrorMessages.CONSTRAINT_FAILED,
					new MapBuilder().add("error", errorMessage).build());
		});
		convertToLocale(errors.getErrors());
		return new ResponseEntity<>(errors.getErrors(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleConstraintViolationException(
			ConstraintViolationException constraintViolationException) {
		Errors errors = new Errors();
		for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
			errors.addError(ApplicationErrorMessages.CONSTRAINT_FAILED,
					new MapBuilder().add("error", violation.getMessage()).build());
		}
		convertToLocale(errors.getErrors());
		return new ResponseEntity<>(errors.getErrors(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
		Errors errors = new Errors();
		String error = methodArgumentTypeMismatchException.getName() + " Should be of type "
				+ methodArgumentTypeMismatchException.getRequiredType().getName();
		errors.addError(ApplicationErrorMessages.CONSTRAINT_FAILED, new MapBuilder().add("error", error).build());
		convertToLocale(errors.getErrors());
		return new ResponseEntity<>(errors.getErrors(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<List<in.taskoo.core.exception.Error>> handleHttpMessageNotReadable(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		Errors errors = new Errors();
		String error = httpMessageNotReadableException.getMessage();
		errors.addError(ApplicationErrorMessages.CONSTRAINT_FAILED, new MapBuilder().add("error", error).build());
		convertToLocale(errors.getErrors());
		return new ResponseEntity<>(errors.getErrors(), HttpStatus.BAD_REQUEST);
	}

	private void convertToLocale(List<Error> errorList) {
		Locale locale = LocaleUtils.toLocale(request.getHeader("locale"));
		if (locale == null) {
			return;
		}
		for (Error error : errorList) {
			String placeholderMessage = messageSource.getMessage(error.getCode(), null, locale);
			// When no message found for respective key messageSource will returns the key
			if (error.getCode().equals(placeholderMessage)) {
				error.setMessage(error.getPlaceholderMessage());
				return;
			}
			if (error.getPlaceholders() == null || error.getPlaceholders().isEmpty()) {
				error.setMessage(placeholderMessage);
			} else {
				for (final Map.Entry<String, String> entry : error.getPlaceholders().entrySet()) {
					String placeholderString = "{" + entry.getKey() + "}";
					placeholderMessage = placeholderMessage.replace(placeholderString,
							entry.getValue() == null ? "null" : entry.getValue());
				}
				error.setMessage(placeholderMessage);
			}
		}

	}
}