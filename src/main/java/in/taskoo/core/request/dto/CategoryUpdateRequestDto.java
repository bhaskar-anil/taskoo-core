package in.taskoo.core.request.dto;

import javax.validation.constraints.Size;

import in.taskoo.core.constant.CommissionType;
import lombok.Data;

@Data
public class CategoryUpdateRequestDto {

		@Size(min=1,max=150, message="title length should be [1-150]")
		private String name;
		
		@Size(max=2000, message="description max length 2000")
		private String description;
		
		private Long commission;
		
		private CommissionType commissionType;
}
