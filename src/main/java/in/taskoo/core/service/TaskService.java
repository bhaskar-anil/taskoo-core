package in.taskoo.core.service;

import java.util.List;

import org.springframework.data.domain.Page;

import in.taskoo.core.request.dto.AdminTaskUpdateRequestDto;
import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.request.dto.TaskQuestionRequestDto;
import in.taskoo.core.request.dto.TaskUpdateRequestDto;
import in.taskoo.core.response.dto.OfferResponseDto;
import in.taskoo.core.response.dto.ResponseDto;
import in.taskoo.core.response.dto.TaskQuestionResponseDto;
import in.taskoo.core.response.dto.TaskResponseDto;

public interface TaskService {
  ResponseDto create(TaskCreateRequestDto taskCreateRequestDto);

  Boolean update(TaskUpdateRequestDto taskUpdateRequestDto, Long taskId);

  TaskResponseDto get(Long taskId);

  Boolean delete(Long taskId);

  Boolean update(AdminTaskUpdateRequestDto adminTaskUpdateRequestDto, Long taskId);

  Boolean cancelTask(Long taskId);

  Page<OfferResponseDto> getOffers(Long taskId);

  Boolean saveQuestion(Long taskId, TaskQuestionRequestDto taskQuestionRequestDto);

  List<TaskQuestionResponseDto> getQuestions(Long taskId);

  List<TaskResponseDto> search(String query, Integer pageNumber, Integer pageSize) throws Exception;

  void saveToElasticSearch(TaskCreateRequestDto task) throws Exception;
}
