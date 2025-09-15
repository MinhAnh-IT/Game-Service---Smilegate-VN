package com.smilegate.game_service.web.controller;

import com.smilegate.game_service.application.dto.request.GameRequest;
import com.smilegate.game_service.application.dto.request.GameUpdateRequest;
import com.smilegate.game_service.application.dto.response.GameResponse;
import com.smilegate.game_service.application.service.game.GameCommandService;
import com.smilegate.game_service.application.service.game.GameQueryService;
import com.smilegate.game_service.common.response.ApiResponse;
import com.smilegate.game_service.common.response.PagedResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/games")
public class GameController {

    GameCommandService gameCommandService;
    GameQueryService gameQueryService;

    // CREATE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<GameResponse>> createGame(@Valid @ModelAttribute GameRequest request) {
        var result = gameCommandService.createGame(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<GameResponse>> updateGame(
            @PathVariable String id,
            @Valid @ModelAttribute GameUpdateRequest request) {
        var result = gameCommandService.updateGame(id, request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // DELETE ONE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGame(@PathVariable String id) {
        gameCommandService.deleteGame(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // DELETE MULTIPLE
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteGames(@RequestBody Set<String> ids) {
        gameCommandService.deleteGames(ids);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // GET LIST
    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<GameResponse>>> getGames(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        log.info("Get games with category: {}, keyword: {}, pageable: {}", category, keyword, pageable);
        var result = gameQueryService.getGames(category, keyword, pageable);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // GET DETAIL
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GameResponse>> getGameById(@PathVariable String id) {
        var result = gameQueryService.getGameById(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
