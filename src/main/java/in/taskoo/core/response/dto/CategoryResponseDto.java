package in.taskoo.core.response.dto;

import in.taskoo.core.constant.CommissionType;
import lombok.Data;

@Data
public class CategoryResponseDto {
	private Long id;
	private String name;
	private String description;
	private Long commission;
	private CommissionType commissionType;
}
