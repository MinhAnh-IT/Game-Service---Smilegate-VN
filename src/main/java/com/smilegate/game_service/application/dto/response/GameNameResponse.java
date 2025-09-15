package com.smilegate.game_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameNameResponse {
    Long id;
    String language;
    String value;
    boolean defaultName;
}
