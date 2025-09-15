package com.smilegate.game_service.application.service.gameName;

import com.smilegate.game_service.application.dto.request.GameNameRequest;
import com.smilegate.game_service.application.dto.response.GameNameResponse;
import com.smilegate.game_service.domain.model.GameName;

public interface GameNameCommandService {
    GameName addGameName(String gameId, GameNameRequest request);
    void deleteGameName(Long id);
    GameNameResponse updateGameName(Long id, GameNameRequest request);
    GameNameResponse addGameNameAndResponseDto(String gameId, GameNameRequest request);
}
