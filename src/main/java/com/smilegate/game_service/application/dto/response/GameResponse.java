package com.smilegate.game_service.application.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameResponse {
    String gameId;
    CategoryResponse category;
    String image;
    List<GameNameResponse> gameNames;
}
