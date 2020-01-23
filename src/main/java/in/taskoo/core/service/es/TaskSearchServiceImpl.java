package in.taskoo.core.service.es;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.taskoo.core.repository.es.TaskSearchRepository;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TaskSearchServiceImpl implements TaskSearchService {
  private final TaskSearchRepository taskSearchRepository;
  @Override
  public List<TaskCreateRequestDto> search(String query, Pageable pageable, String... select) throws Exception {
    return taskSearchRepository.search(query, pageable, select).getTasks();
  }
  @Override
  public void create(TaskCreateRequestDto task) throws Exception {
    taskSearchRepository.create(task);
  }
  @Override
  public void update(TaskCreateRequestDto task) throws Exception {
    taskSearchRepository.update(task);
  }
}
