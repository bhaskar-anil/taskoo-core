package in.taskoo.core.service.es;

import java.util.List;

import org.springframework.data.domain.Pageable;

import in.taskoo.core.request.dto.TaskCreateRequestDto;

public interface TaskSearchService {
  List<TaskCreateRequestDto> search(String query, Pageable pageable, String... select) throws Exception;
  void create(TaskCreateRequestDto task) throws Exception;
  void update(TaskCreateRequestDto task) throws Exception;
}
