package in.taskoo.core.constant;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskType {
	REMOTE(1),
	IN_PERSON(2);
	
	private Integer id;

	@JsonCreator
	public EstimateType toEnum(Integer id) {
		return Arrays.stream(EstimateType.values()).filter(type -> type.getId().equals(id)).findFirst().orElse(null);
	}

	@JsonValue
	public String getTaskStatus() {
		return this.toString();
	}
}
