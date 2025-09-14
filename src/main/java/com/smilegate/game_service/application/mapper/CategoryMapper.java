package com.smilegate.game_service.application.mapper;

import com.smilegate.game_service.application.dto.response.CategoryResponse;
import com.smilegate.game_service.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
}
