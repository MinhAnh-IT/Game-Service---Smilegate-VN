package com.smilegate.game_service.application.mapper;

import com.smilegate.game_service.application.dto.request.CategoryRequest;
import com.smilegate.game_service.application.dto.response.CategoryResponse;
import com.smilegate.game_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "code", ignore = true)
    Category toEntity(CategoryRequest request);
}
