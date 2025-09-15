package com.smilegate.game_service.application.mapper;

import com.smilegate.game_service.application.dto.request.GameNameRequest;
import com.smilegate.game_service.application.dto.response.GameNameResponse;
import com.smilegate.game_service.domain.model.GameName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GameNameMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "language.code", source = "language")
    GameName toEntity(GameNameRequest request);

    @Mapping(target = "language", source = "language.code")
    GameNameResponse toResponse(GameName entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "defaultName", ignore = true)
    @Mapping(target = "language.code", source = "language")
    void updateEntityFromRequest(GameNameRequest request, @MappingTarget GameName entity);

}
