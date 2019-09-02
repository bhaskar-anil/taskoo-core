package in.taskoo.core.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.service.TaskService;

@RestController("/task")
class TaskController{

	@Resource
	private TaskService taskService;
	
    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody @Valid TaskCreateRequestDto taskCreateRequestDto){
    	return ResponseEntity.ok(taskService.create(taskCreateRequestDto).getId());
    }
    
}