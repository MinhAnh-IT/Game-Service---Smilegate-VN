package com.smilegate.game_service.application.mapper;

import com.smilegate.game_service.application.dto.response.LanguageResponse;
import com.smilegate.game_service.domain.model.Language;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageResponse toResponse(Language language);
}
