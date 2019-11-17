package in.taskoo.core.service;

import in.taskoo.core.request.dto.CategoryCreateRequestDto;
import in.taskoo.core.request.dto.CategoryUpdateRequestDto;
import in.taskoo.core.response.dto.CategoryResponseDto;

public interface CategoryService {
	public Boolean create(CategoryCreateRequestDto categoryRequestDto);
	public Boolean update(CategoryUpdateRequestDto categoryRequestDto, Long categoryId);
	public CategoryResponseDto get(Long categoryId);
	public Boolean delete(Long categoryId);
}
