package in.taskoo.core.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.request.dto.TaskCreateRequestDto;
import in.taskoo.core.response.dto.TaskResponseDto;
import in.taskoo.core.service.TaskService;

@RestController
@RequestMapping("/task")
class TaskController{

	@Resource
	private TaskService taskService;
	
    @PostMapping
    public ResponseEntity<Long> createTask(@RequestBody @Valid TaskCreateRequestDto taskCreateRequestDto){
    	return ResponseEntity.ok(taskService.create(taskCreateRequestDto).getId());
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable("taskId") Long taskId){
    	return ResponseEntity.ok(taskService.get(taskId));
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Long> deleteTask(@PathVariable("taskId") Long taskId){
    	return ResponseEntity.ok(taskService.delete(taskId).getId());
    }
    
}