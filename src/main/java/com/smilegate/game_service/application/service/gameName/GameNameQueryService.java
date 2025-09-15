package com.smilegate.game_service.application.service.gameName;

import com.smilegate.game_service.application.dto.response.GameNameResponse;
import java.util.List;

public interface GameNameQueryService {
    List<GameNameResponse> getByGameId(String gameId);
    GameNameResponse getDefaultName(String gameId);
    GameNameResponse getByLanguage(String gameId, String language);
}
