package in.taskoo.core.repository.es;

import org.springframework.data.domain.Pageable;

import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.response.dto.Tasks;

public interface TaskSearchRepository {
  Tasks search(String query, Pageable pageable, String... select) throws Exception;
  void create(TaskCreateRequestDto taskResponseDto) throws Exception;
  void update(TaskCreateRequestDto taskResponseDto) throws Exception;
}
