package in.taskoo.core.service.es;

import java.util.List;

import org.springframework.stereotype.Service;

import in.taskoo.core.repository.TaskRespository;
import in.taskoo.core.repository.es.TaskSearchRepository;
import in.taskoo.core.response.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TaskSearchServiceImpl implements TaskSearchService {
  private final TaskSearchRepository taskSearchRepository;
  private final TaskRespository taskRespository;

  @Override
  public List<TaskResponseDto> search(String query) throws Exception {
    List<Long> taskIds = taskSearchRepository.search(query, null).getTaskIds();
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void create(TaskResponseDto task) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(TaskResponseDto task) throws Exception {
    // TODO Auto-generated method stub

  }
}
