package com.smilegate.game_service.application.service.game;

import com.smilegate.game_service.application.dto.response.GameResponse;
import com.smilegate.game_service.common.response.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface GameQueryService {
    PagedResponse<GameResponse> getGames(String category, String keyword, Pageable pageable);
    GameResponse getGameById(String id);
}
