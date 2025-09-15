package com.smilegate.game_service.application.service.category;

import com.smilegate.game_service.application.dto.request.CategoryRequest;
import com.smilegate.game_service.application.dto.response.CategoryResponse;

public interface CategoryCommandService {
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(String code, CategoryRequest request);
    void deleteCategory(String code);
}
