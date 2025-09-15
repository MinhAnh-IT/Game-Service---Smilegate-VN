package com.smilegate.game_service.application.service.game;

import com.smilegate.game_service.application.dto.request.GameRequest;
import com.smilegate.game_service.application.dto.request.GameUpdateRequest;
import com.smilegate.game_service.application.dto.response.GameResponse;

import java.util.Set;

public interface GameCommandService {
    GameResponse createGame(GameRequest request);
    GameResponse updateGame(String id, GameUpdateRequest request);
    void deleteGame(String id);
    void deleteGames(Set<String> ids);
}
