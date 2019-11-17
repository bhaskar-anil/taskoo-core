package in.taskoo.core.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.request.dto.AdminTaskUpdateRequestDto;
import in.taskoo.core.request.dto.CategoryCreateRequestDto;
import in.taskoo.core.request.dto.CategoryUpdateRequestDto;
import in.taskoo.core.service.CategoryService;
import in.taskoo.core.service.TaskService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
class AdminController {

	private final TaskService taskService;
	
	private final CategoryService categoryService;

	@PatchMapping("/tasks/{taskId}")
	public ResponseEntity<Boolean> updateTask(@RequestBody @Valid AdminTaskUpdateRequestDto adminTaskUpdateRequestDto ,@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.update(adminTaskUpdateRequestDto, taskId));
	}
	
	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable("taskId") Long taskId) {
		return ResponseEntity.ok(taskService.delete(taskId));
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Boolean> createCategory(@RequestBody @Valid CategoryCreateRequestDto categoryRequestDto){
		return ResponseEntity.ok(categoryService.create(categoryRequestDto));
	}
	
	@PatchMapping("/categories/{categoryId}")
	public ResponseEntity<Boolean> updateCategory(@RequestBody  @Valid CategoryUpdateRequestDto categoryRequestDto,@PathVariable("categoryId") Long categoryId){
		return ResponseEntity.ok(categoryService.update(categoryRequestDto,categoryId));
	}

}