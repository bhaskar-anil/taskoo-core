package in.taskoo.core.response.dto;

import java.util.List;

import in.taskoo.core.request.dto.TaskCreateRequestDto;
import lombok.Data;

@Data
public class Tasks {
  private List<TaskCreateRequestDto> tasks;
}
