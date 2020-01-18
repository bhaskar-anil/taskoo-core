package in.taskoo.core.service.es;

import java.util.List;

import in.taskoo.core.response.dto.TaskResponseDto;

public interface TaskSearchService {
  List<TaskResponseDto> search(String query) throws Exception;
  void create(TaskResponseDto task) throws Exception;
  void update(TaskResponseDto task) throws Exception;
}
