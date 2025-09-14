package com.smilegate.game_service.application.service.category.Impl;

import com.smilegate.game_service.application.dto.response.CategoryResponse;
import com.smilegate.game_service.application.mapper.CategoryMapper;
import com.smilegate.game_service.application.service.category.CategoryQueryService;
import com.smilegate.game_service.application.service.category.CategoryValidator;
import com.smilegate.game_service.domain.repository.CategoryRepository;
import com.smilegate.game_service.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryQueryService, CategoryValidator {
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
            throw new NotFoundException(code);
        }
    }
}
