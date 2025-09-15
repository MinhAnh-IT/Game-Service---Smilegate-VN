package com.smilegate.game_service.web.controller;

import com.smilegate.game_service.application.service.language.LanguageQueryService;
import com.smilegate.game_service.common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/languages")
public class LanguageController {
    LanguageQueryService languageService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getLanguages() {
        var languages = languageService.getAllLanguages();
        return ResponseEntity.ok(ApiResponse.success(languages));
    }
}
