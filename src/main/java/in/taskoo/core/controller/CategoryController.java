package in.taskoo.core.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.taskoo.core.response.dto.CategoryResponseDto;
import in.taskoo.core.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponseDto> getCategory(@PathParam("categoryId") Long categoryId){
		return ResponseEntity.ok(categoryService.get(categoryId));
	}
}