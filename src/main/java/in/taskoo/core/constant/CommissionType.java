package in.taskoo.core.constant;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.exception.NoDataFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommissionType {
	PERCENTAGE (1), 
	CPA (2); 
	
	private Integer id;
	
	@JsonCreator
	public CommissionType toEnum(Integer id) {
		return Arrays.stream(CommissionType.values()).filter(status -> status.getId().equals(id)).findFirst().orElseThrow(()-> NoDataFoundException.getException(ApplicationErrorMessages.NO_DATA_FOUND));
	}

	@JsonValue
	public String getTaskStatus() {
		return this.toString();
	}
}
