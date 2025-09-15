package com.smilegate.game_service.application.service.game.impl;

import com.smilegate.game_service.application.dto.request.GameRequest;
import com.smilegate.game_service.application.dto.request.GameUpdateRequest;
import com.smilegate.game_service.application.dto.response.GameResponse;
import com.smilegate.game_service.application.mapper.GameMapper;
import com.smilegate.game_service.application.mapper.GameNameMapper;
import com.smilegate.game_service.application.service.game.GameCommandService;
import com.smilegate.game_service.application.service.game.GameQueryService;
import com.smilegate.game_service.application.service.gameName.GameNameCommandService;
import com.smilegate.game_service.application.service.gameName.GameNameQueryService;
import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.common.response.PagedResponse;
import com.smilegate.game_service.domain.model.Category;
import com.smilegate.game_service.domain.model.Game;
import com.smilegate.game_service.domain.model.GameName;
import com.smilegate.game_service.domain.repository.CategoryRepository;
import com.smilegate.game_service.domain.repository.GameRepository;
import com.smilegate.game_service.exception.CustomException;
import com.smilegate.game_service.infrastructure.storage.StorageService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameServiceImpl implements GameCommandService, GameQueryService {

    GameRepository gameRepository;
    CategoryRepository categoryRepository;
    StorageService storageService;
    GameMapper gameMapper;
    GameNameMapper gameNameMapper;

    // CREATE GAME
    @Override
    @Transactional
    public GameResponse createGame(GameRequest request) {
        Game game = buildBaseGame(request.getCategory(), request.getImage(), null);

        // Build GameNames without saving
        List<GameName> names = request.getGameNames().stream()
                .map(req -> {
                    GameName gameName = gameNameMapper.toEntity(req);
                    gameName.setGame(game);
                    return gameName;
                })
                .toList();

        game.setNames(names);
        Game saved = gameRepository.save(game); // cascade saves names
        return gameMapper.toResponse(saved);
    }

    // UPDATE GAME
    @Override
    @Transactional
    public GameResponse updateGame(String id, GameUpdateRequest request) {
        Game existing = gameRepository.findByIdWithNames(id)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND, id));

        // update category
        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_CATEGORY, request.getCategory()));
        existing.setCategory(category);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String imagePath = storageService.store(request.getImage());
            existing.setImage(imagePath);
        }

        // save & return
        Game saved = gameRepository.save(existing);
        return gameMapper.toResponse(saved);
    }


    // DELETE ONE GAME
    @Override
    @Transactional
    public void deleteGame(String id) {
        if (!gameRepository.existsById(id)) {
            throw new CustomException(StatusCode.NOT_FOUND, id);
        }
        gameRepository.deleteById(id);
    }

    // DELETE MULTIPLE GAMES
    @Override
    @Transactional
    public void deleteGames(Set<String> ids) {
        ids.forEach(id -> {
            if (!gameRepository.existsById(id)) {
                throw new CustomException(StatusCode.NOT_FOUND, id);
            }
        });
        gameRepository.deleteAllById(ids);
    }

    // GET GAME LIST WITH FILTERS + PAGINATION
    @Override
    public PagedResponse<GameResponse> getGames(String category, String keyword, Pageable pageable) {
        var games = (category != null && keyword != null)
                ? gameRepository.findByCategory_CodeAndNames_ValueContainingIgnoreCase(category, keyword, pageable)
                : (category != null)
                ? gameRepository.findByCategory_Code(category, pageable)
                : (keyword != null)
                ? gameRepository.findByNames_ValueContainingIgnoreCase(keyword, pageable)
                : gameRepository.findAll(pageable);

        var content = games.map(gameMapper::toResponse).toList();

        return PagedResponse.<GameResponse>builder()
                .content(content)
                .page(games.getNumber())
                .size(games.getSize())
                .totalElements(games.getTotalElements())
                .totalPages(games.getTotalPages())
                .last(games.isLast())
                .build();
    }

    // GET GAME DETAIL
    @Override
    public GameResponse getGameById(String id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND, id));
        return gameMapper.toResponse(game);
    }

    // PRIVATE HELPER
    private Game buildBaseGame(String categoryCode, org.springframework.web.multipart.MultipartFile image, String existingId) {
        // Validate category
        Category category = categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_CATEGORY, categoryCode));

        // Upload image if provided
        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            imagePath = storageService.store(image);
        }

        return Game.builder()
                .id(existingId != null ? existingId : UUID.randomUUID().toString())
                .category(category)
                .image(imagePath)
                .build();
    }
}
