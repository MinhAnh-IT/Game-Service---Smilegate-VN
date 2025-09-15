package com.smilegate.game_service.application.dto.request;

import com.smilegate.game_service.validation.annotation.ValidGameNames;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameRequest {
    @NotBlank(message = "Category must not be empty")
    String category;
    MultipartFile image;
    @ValidGameNames
    List<GameNameRequest> gameNames;
}
