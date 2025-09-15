package com.smilegate.game_service.web.controller;

import com.smilegate.game_service.application.dto.request.GameNameRequest;
import com.smilegate.game_service.application.dto.response.GameNameResponse;
import com.smilegate.game_service.application.service.gameName.GameNameCommandService;
import com.smilegate.game_service.application.service.gameName.GameNameQueryService;
import com.smilegate.game_service.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/games/{gameId}/names")
public class GameNameController {

    GameNameCommandService gameNameCommandService;
    GameNameQueryService gameNameQueryService;

    // Add a new GameName for a specific game
    @PostMapping
    public ResponseEntity<ApiResponse<GameNameResponse>> addGameName(
            @PathVariable String gameId,
            @Valid @RequestBody GameNameRequest request) {

        var response = gameNameCommandService.addGameNameAndResponseDto(gameId, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // Update an existing GameName by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GameNameResponse>> updateGameName(
            @PathVariable Long id,
            @Valid @RequestBody GameNameRequest request) {
        var result = gameNameCommandService.updateGameName(id, request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // Delete a GameName by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGameName(@PathVariable Long id) {
        gameNameCommandService.deleteGameName(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // Get all GameNames of a game by gameId
    @GetMapping
    public ResponseEntity<ApiResponse<List<GameNameResponse>>> getByGameId(@PathVariable String gameId) {
        var result = gameNameQueryService.getByGameId(gameId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    //Get the default GameName of a game
    @GetMapping("/default")
    public ResponseEntity<ApiResponse<GameNameResponse>> getDefaultName(@PathVariable String gameId) {
        var result = gameNameQueryService.getDefaultName(gameId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // Get a GameName of a game by language
    @GetMapping("/lang/{language}")
    public ResponseEntity<ApiResponse<GameNameResponse>> getByLanguage(
            @PathVariable String gameId,
            @PathVariable String language) {
        var result = gameNameQueryService.getByLanguage(gameId, language);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
