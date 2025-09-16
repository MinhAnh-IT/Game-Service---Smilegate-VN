package com.smilegate.game_service.application.mapper;

import org.mapstruct.Mapper;

import com.smilegate.game_service.application.dto.response.GameNameResponse;
import com.smilegate.game_service.application.dto.response.GameResponse;
import com.smilegate.game_service.domain.model.Game;
import com.smilegate.game_service.domain.model.GameName;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(source = "id", target = "gameId")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "names", target = "gameNames")
    GameResponse toResponse(Game game);

    @Mapping(source = "language.code", target = "language")
    GameNameResponse toGameNameResponse(GameName gameName);

    List<GameNameResponse> toGameNameResponses(List<GameName> names);
}
