package com.smilegate.game_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameUpdateRequest {

    @NotBlank(message = "Category must not be empty")
    String category;

    MultipartFile image;
}
