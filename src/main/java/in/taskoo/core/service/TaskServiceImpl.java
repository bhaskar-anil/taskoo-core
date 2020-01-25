package in.taskoo.core.service;

import static in.taskoo.core.error.message.ApplicationErrorMessages.NO_DATA_FOUND;
import static in.taskoo.core.exception.ForbiddenException.throwException;
import static in.taskoo.core.exception.NoDataFoundException.getException;
import static java.lang.Boolean.FALSE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import in.taskoo.core.constant.TaskStatus;
import in.taskoo.core.context.AppContext;
import in.taskoo.core.entity.Category;
import in.taskoo.core.entity.Offer;
import in.taskoo.core.entity.OfferComment;
import in.taskoo.core.entity.Task;
import in.taskoo.core.entity.TaskHistory;
import in.taskoo.core.entity.TaskQuestion;
import in.taskoo.core.error.message.ApplicationErrorMessages;
import in.taskoo.core.repository.CategoryRespository;
import in.taskoo.core.repository.OfferRespository;
import in.taskoo.core.repository.TaskHistoryRespository;
import in.taskoo.core.repository.TaskQuestionRepository;
import in.taskoo.core.repository.TaskRespository;
import in.taskoo.core.request.dto.AdminTaskUpdateRequestDto;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.request.dto.TaskQuestionRequestDto;
import in.taskoo.core.request.dto.TaskUpdateRequestDto;
import in.taskoo.core.response.dto.OfferCommentResponseDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.response.dto.ResponseDto;
import in.taskoo.core.response.dto.TaskQuestionResponseDto;
import in.taskoo.core.response.dto.TaskResponseDto;
import in.taskoo.core.service.es.TaskSearchService;
import in.taskoo.core.util.DozzerUtil;
import in.taskoo.core.util.MapBuilder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(isolation=Isolation.READ_COMMITTED)
public class TaskServiceImpl implements TaskService {

	private final TaskRespository taskRespository;
	
	private final TaskHistoryRespository taskHistoryRespository;
	
	private final Mapper mapper;
	
	private final OfferRespository offerRespository;
	
	private final CategoryRespository categoryRespository;
	
	private final TaskQuestionRepository taskQuestionRepository;
	
  private final DozzerUtil<OfferComment, OfferCommentResponseDto> dozzerUtil;

  private final DozzerUtil<Task, TaskResponseDto> taskDozzer;
	
  private final TaskSearchService taskSearchService;

	@Override
	public ResponseDto create(TaskCreateRequestDto taskCreateRequestDto) {
		Task task = mapper
				.map(taskCreateRequestDto, Task.class)
				.setCategory(getCategory(taskCreateRequestDto.getCategoryId()))
				.setTaskDateTime(LocalDateTime.now());
		Task savedTask = taskRespository.save(task);
		saveToHistory(savedTask);
		// TODO call TaskSearchDaoImpl.create to push the new task to elastic search data
    return new ResponseDto().setId(task.getId());
	}

	@Override
	public TaskResponseDto get(Long taskId) {
		Task task = getTask(taskId);
		return mapper.map(task, TaskResponseDto.class);
	}

	@Override
	public Page<OfferResponseDto> getOffers(Long taskId) {
		Task task = getTask(taskId);
		Pageable pageable = AppContext.pageable.get();
		Page<OfferResponseDto> offerPage = offerRespository.findByTaskAndDeleteFlag(task,FALSE,pageable).map(new Function<Offer, OfferResponseDto>() {
			@Override
			public OfferResponseDto apply(Offer offer) {
				return mapper.map(offer, OfferResponseDto.class);
			}
			
		});
		return offerPage;
	}

	@Override
	public Boolean delete(Long taskId) {
		Task task = getTask(taskId);
		task.setDeleteFlag(Boolean.TRUE);
		taskRespository.save(task);
		return Boolean.TRUE;
	}

	@Override
	public Boolean update(TaskUpdateRequestDto taskUpdateRequestDto, Long taskId) {
		Task task = getTask(taskId);
		Task savedTask = null;
		if(task.getTaskStatus().isAllowedUpdate()) {
			mapper.map(taskUpdateRequestDto, task);
			savedTask = taskRespository.save(task);
			saveToHistory(savedTask);
		  // TODO call TaskSearchDaoImpl.update to push the task update to elastic search data
		}else {
			throwException(ApplicationErrorMessages.TASK_UPDATE_NOT_ALLOWED,
					new MapBuilder().add("status", task.getTaskStatus()).build());
		}
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean update(AdminTaskUpdateRequestDto adminTaskUpdateRequestDto, Long taskId) {
		Task task = getTask(taskId);
		if(Objects.nonNull(adminTaskUpdateRequestDto.getCategoryId())) {
			Category category = categoryRespository.findById(adminTaskUpdateRequestDto.getCategoryId()).orElseThrow(()->getException(NO_DATA_FOUND));
			task.setCategory(category);
		}
		mapper.map(adminTaskUpdateRequestDto, task);
		taskRespository.save(task);
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean cancelTask(Long taskId) {
		Task task = getTask(taskId);
		Task savedTask = null;
		if(task.getTaskStatus().isAllowedCancel()) {
			task.setTaskStatus(TaskStatus.CANCELLED);
			savedTask = taskRespository.save(task);
			saveToHistory(savedTask);
		}else {
			throwException(ApplicationErrorMessages.TASK_CANCEL_NOT_ALLOWED);
		}
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean saveQuestion(Long taskId, TaskQuestionRequestDto taskQuestionRequestDto) {
		Task task = getTask(taskId);
		TaskQuestion taskQuestion = mapper.map(taskQuestionRequestDto, TaskQuestion.class)
				.setCreateDateTime(LocalDateTime.now())
				.setTask(task);
		taskQuestionRepository.save(taskQuestion);
		return Boolean.TRUE;
	}
	
	@Override
	public List<TaskQuestionResponseDto> getQuestions(Long taskId) {
		Task task = getTask(taskId);
		List<TaskQuestionResponseDto> responseDtos = new ArrayList<>();
		taskQuestionRepository.findByTaskAndDeleteFlagOrderByIdDesc(task, false).stream().forEachOrdered(question ->{
			responseDtos.add(mapper.map(question, TaskQuestionResponseDto.class));
		});
		return responseDtos;
	}

	
	private void saveToHistory(Task savedTask) {
		TaskHistory history = mapper.map(savedTask, TaskHistory.class).setTask(savedTask);
		history.setId(null);
		taskHistoryRespository.save(history);
	}
	
	private Category getCategory(Long categoryId) {
		return categoryRespository.findByIdAndDeleteFlag(categoryId,Boolean.FALSE)
				.orElseThrow(()->getException(NO_DATA_FOUND));
	}
	
	private Task getTask(Long taskId) {
		return taskRespository.findByIdAndDeleteFlag(taskId,FALSE)
				.orElseThrow(()->getException(NO_DATA_FOUND));
	}

  @Override
  public List<TaskResponseDto> search(String query, Integer pageNumber, Integer pageSize) throws Exception {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    List<TaskCreateRequestDto> tasksFromEs = taskSearchService.search(query, pageable, new String[] { "id", "title" });
    List<Long> taskIds = tasksFromEs.stream().map(task -> task.getId()).collect(Collectors.toList());
    List<Task> tasks = (List<Task>) taskRespository.findAllById(taskIds);
    return taskDozzer.mapList(tasks, TaskResponseDto.class);
  }

  @Override
  public void saveToElasticSearch(TaskCreateRequestDto task) throws Exception {
    taskSearchService.create(task);
  }
	
}
