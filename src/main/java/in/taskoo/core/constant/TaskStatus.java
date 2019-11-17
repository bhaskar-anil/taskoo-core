package in.taskoo.core.constant;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
	CREATED (1,TRUE,TRUE), 
	OPEN (2,TRUE,TRUE), 
	ACCEPTED (3,FALSE,TRUE), 
	COMPLETED (4,FALSE,FALSE), 
	EXPIRED (5,FALSE,FALSE), 
	CANCELLED (6,FALSE,FALSE),
	CLOSED (7,FALSE,FALSE); 
	
	private Integer id;
	private Boolean allowedUpdate;
	private Boolean allowedCancel;

	public Boolean isAllowedUpdate() {
		return this.allowedUpdate;
	}

	public boolean isAllowedCancel() {
		return this.allowedCancel;
	}
	
	@JsonCreator
	public TaskStatus toEnum(Integer id) {
		return Arrays.stream(TaskStatus.values()).filter(status -> status.getId().equals(id)).findFirst().orElseThrow(()-> NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
	}

	@JsonValue
	public String getTaskStatus() {
		return this.toString();
	}
}
