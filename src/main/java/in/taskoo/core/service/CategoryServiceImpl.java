package in.taskoo.core.service;

import static in.taskoo.core.error.message.ApplicationErrorMessages.NO_DATA_FOUND;
import static in.taskoo.core.exception.NoDataFoundException.getException;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import in.taskoo.core.entity.Category;
import in.taskoo.core.entity.CategoryHistory;
import in.taskoo.core.repository.CategoryHistoryRespository;
import in.taskoo.core.repository.CategoryRespository;
import in.taskoo.core.request.dto.CategoryCreateRequestDto;
import in.taskoo.core.request.dto.CategoryUpdateRequestDto;
import in.taskoo.core.response.dto.CategoryResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(isolation=Isolation.READ_COMMITTED)
public class CategoryServiceImpl implements CategoryService {

	private final Mapper mapper;
	
	private final CategoryRespository categoryRespository;
	
	private final CategoryHistoryRespository categoryHistoryRespository;

	@Override
	public Boolean create(CategoryCreateRequestDto categoryRequestDto) {
		Category category = mapper.map(categoryRequestDto, Category.class);
		Category savedCategory = categoryRespository.save(category);
		saveToHistory(savedCategory);
		return Boolean.TRUE;
	}

	@Override
	public Boolean update(CategoryUpdateRequestDto categoryRequestDto, Long categoryId) {
		Category category = categoryRespository.findById(categoryId).orElseThrow(()->getException(NO_DATA_FOUND));
		mapper.map(categoryRequestDto, category);
		Category savedCategory = categoryRespository.save(category);
		this.saveToHistory(savedCategory);
		return Boolean.TRUE;
	}

	@Override
	public CategoryResponseDto get(Long categoryId) {
		Category category = categoryRespository.findById(categoryId).orElseThrow(()->getException(NO_DATA_FOUND));
		return mapper.map(category, CategoryResponseDto.class);
	}

	@Override
	public Boolean delete(Long categoryId) {
		Category category = categoryRespository.findById(categoryId).orElseThrow(()->getException(NO_DATA_FOUND));
		category.setDeleteFlag(Boolean.TRUE);
		Category savedCategory = categoryRespository.save(category);
		this.saveToHistory(savedCategory);
		return Boolean.TRUE;
	}

	
	private void saveToHistory(Category savedCategory) {
		CategoryHistory history = mapper.map(savedCategory, CategoryHistory.class).setCategory(savedCategory);
		history.setId(null);
		categoryHistoryRespository.save(history);
	}
}
