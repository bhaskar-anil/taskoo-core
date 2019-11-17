package in.taskoo.core.request.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import in.taskoo.core.constant.CommissionType;
import lombok.Data;

@Data
public class CategoryCreateRequestDto {

		@NotNull(message = "title can't be null")
		@Size(min=1,max=150, message="title length should be [1-150]")
		private String name;
		
		@NotNull(message = "description can't be null")
		@Size(max=2000, message="description max length 2000")
		private String description;
		
		@NotNull(message = "taskType can't be null")
		private Long commission;
		
		@NotNull(message = "location can't be blank")
		private CommissionType commissionType;
}
