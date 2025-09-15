package com.smilegate.game_service.application.dto.request;

import com.smilegate.game_service.validation.annotation.ValidGameNameValue;
import com.smilegate.game_service.validation.annotation.ValidLanguageCode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameNameRequest {

    @ValidLanguageCode
    String language;

    @ValidGameNameValue
    String value;

    @NotNull(message = "isDefault flag must not be null")
    Boolean defaultName;
}
