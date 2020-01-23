package in.taskoo.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.annotation.Paged;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.request.dto.TaskQuestionRequestDto;
import in.taskoo.core.request.dto.TaskUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.response.dto.ResponseDto;
import in.taskoo.core.response.dto.TaskQuestionResponseDto;
import in.taskoo.core.response.dto.TaskResponseDto;
import in.taskoo.core.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
class TaskController {

	private final TaskService taskService;

	@PostMapping
  public ResponseEntity<ResponseDto> createTask(@RequestBody @Valid TaskCreateRequestDto taskCreateRequestDto)
      throws Exception {
    ResponseDto response = taskService.create(taskCreateRequestDto);
    taskService.saveToElasticSearch(taskCreateRequestDto.setId(response.getId()));
    return ResponseEntity.ok(response);
	}

	@GetMapping
  public List<TaskResponseDto> search(@RequestParam("query") String query, Integer pageNumber, Integer pageSize)
      throws Exception {
    return taskService.search(query, pageNumber, pageSize);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<TaskResponseDto> getTask(@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.get(taskId));
	}
	
	@Paged
	@GetMapping("/{taskId}/offers")
	public Page<OfferResponseDto> getOffers(@PathVariable("taskId") Long taskId) {
		return taskService.getOffers(taskId);
	}

	@PatchMapping("/{taskId}")
	public ResponseEntity<Boolean> update(@RequestBody @Valid TaskUpdateRequestDto taskUpdateRequestDto,
			@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.update(taskUpdateRequestDto, taskId));
	}

	@PatchMapping("/{taskId}/cancel")
	public ResponseEntity<Boolean> cancelTask(@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.cancelTask(taskId));
	}
	
	@PatchMapping("/{taskId}/question")
	public ResponseEntity<Boolean> question(@PathVariable("taskId") Long taskId,@RequestBody TaskQuestionRequestDto taskQuestionRequestDto) {
		return ResponseEntity.ok(taskService.saveQuestion(taskId, taskQuestionRequestDto));
	}
	
	@GetMapping("/{taskId}/question")
	public List<TaskQuestionResponseDto> saveQuestion(@PathVariable("taskId") Long taskId) {
		return taskService.getQuestions(taskId);
	}

}