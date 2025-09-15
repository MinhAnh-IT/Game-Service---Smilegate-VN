package com.smilegate.game_service.application.service.category.impl;

import com.smilegate.game_service.application.dto.request.CategoryRequest;
import com.smilegate.game_service.application.dto.response.CategoryResponse;
import com.smilegate.game_service.application.mapper.CategoryMapper;
import com.smilegate.game_service.application.service.category.CategoryCommandService;
import com.smilegate.game_service.application.service.category.CategoryQueryService;
import com.smilegate.game_service.application.service.category.CategoryValidator;
import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.domain.repository.CategoryRepository;
import com.smilegate.game_service.exception.CustomException;
import com.smilegate.game_service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryQueryService, CategoryValidator, CategoryCommandService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    // Get all categories and map to DTO list
    @Override
    public List<CategoryResponse> getCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    // Check if category exists by code, throw NotFoundException if not
    @Override
    public void validateExistsByCode(String code) {
        boolean isExits = categoryRepository.existsById(code);
        if(!isExits){
            throw new NotFoundException(StatusCode.NOT_FOUND_CATEGORY,code);
        }
    }

    // Create a new category from request, ensuring unique display name
    @Transactional
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        boolean isExistsDisplayName = categoryRepository.existsByDisplayName(request.getDisplayName());
        if(isExistsDisplayName){
            throw new CustomException(StatusCode.EXISTS_CATEGORY_NAME, request.getDisplayName());
        }
        var category = categoryMapper.toEntity(request);
        category.setCode(UUID.randomUUID().toString());
        var saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    // Update displayName of category from request
    @Transactional
    @Override
    public CategoryResponse updateCategory(String code, CategoryRequest request) {
        var category = categoryRepository.findById(code)
                .orElseThrow(() -> new NotFoundException(StatusCode.NOT_FOUND_CATEGORY, code));

        // check if the new display name already exists in another category
        boolean isExistsDisplayName = categoryRepository
                .existsByDisplayNameAndCodeNot(request.getDisplayName(), code);
        if (isExistsDisplayName) {
            throw new CustomException(StatusCode.EXISTS_CATEGORY_NAME, request.getDisplayName());
        }
        category.setDisplayName(request.getDisplayName());
        var saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    // Delete category by code after validating existence
    @Transactional
    @Override
    public void deleteCategory(String code) {
        validateExistsByCode(code);
        categoryRepository.deleteById(code);
    }
}
