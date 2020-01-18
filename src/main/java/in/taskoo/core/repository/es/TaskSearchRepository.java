package in.taskoo.core.repository.es;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import in.taskoo.core.response.dto.TaskIds;
import in.taskoo.core.response.dto.TaskResponseDto;

public interface TaskSearchRepository {
  TaskIds search(String query, Pageable pageable) throws Exception;
  void create(TaskResponseDto taskResponseDto) throws Exception;
  void update(TaskResponseDto taskResponseDto) throws Exception;
}
