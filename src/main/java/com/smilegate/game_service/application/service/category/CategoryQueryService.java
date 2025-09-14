package com.smilegate.game_service.application.service.category;

import com.smilegate.game_service.application.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryResponse> getCategories();
}
