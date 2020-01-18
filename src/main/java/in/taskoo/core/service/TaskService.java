package in.taskoo.core.service;

import java.util.List;

import org.springframework.data.domain.Page;

import in.taskoo.core.request.dto.AdminTaskUpdateRequestDto;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.request.dto.TaskQuestionRequestDto;
import in.taskoo.core.request.dto.TaskUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.response.dto.TaskQuestionResponseDto;
import in.taskoo.core.response.dto.TaskResponseDto;

public interface TaskService {
	public Boolean create(TaskCreateRequestDto taskCreateRequestDto);
	public Boolean update(TaskUpdateRequestDto taskUpdateRequestDto, Long taskId);
	public TaskResponseDto get(Long taskId);
	public Boolean delete(Long taskId);
	public Boolean update(AdminTaskUpdateRequestDto adminTaskUpdateRequestDto, Long taskId);
	public Boolean cancelTask(Long taskId);
	public Page<OfferResponseDto> getOffers(Long taskId);
	public Boolean saveQuestion(Long taskId, TaskQuestionRequestDto taskQuestionRequestDto);
	public List<TaskQuestionResponseDto> getQuestions(Long taskId);
}
