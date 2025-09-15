package com.smilegate.game_service.application.service.gameName.impl;

import com.smilegate.game_service.application.dto.request.GameNameRequest;
import com.smilegate.game_service.application.dto.response.GameNameResponse;
import com.smilegate.game_service.application.mapper.GameNameMapper;
import com.smilegate.game_service.application.service.gameName.GameNameCommandService;
import com.smilegate.game_service.application.service.gameName.GameNameQueryService;
import com.smilegate.game_service.application.service.language.LanguageValidator;
import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.domain.model.Game;
import com.smilegate.game_service.domain.model.GameName;
import com.smilegate.game_service.domain.repository.GameNameRepository;
import com.smilegate.game_service.exception.GameNameException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameNameServiceImpl implements GameNameCommandService, GameNameQueryService {

    GameNameRepository gameNameRepository;
    GameNameMapper gameNameMapper;
    LanguageValidator languageValidator;

    // ======================
    // Query methods
    // ======================

    @Override
    public List<GameNameResponse> getByGameId(String gameId) {
        return gameNameRepository.findByGame_Id(gameId).stream()
                .map(gameNameMapper::toResponse)
                .toList();
    }

    @Override
    public GameNameResponse getDefaultName(String gameId) {
        var name = gameNameRepository.findByGame_IdAndDefaultNameTrue(gameId)
                .orElseThrow(() -> new GameNameException(StatusCode.GAME_NAME_NOT_FOUND, gameId));
        return gameNameMapper.toResponse(name);
    }

    @Override
    public GameNameResponse getByLanguage(String gameId, String language) {
        var name = gameNameRepository.findByGame_IdAndLanguage_Code(gameId, language)
                .orElseThrow(() -> new GameNameException(StatusCode.GAME_NAME_LANGUAGE_NOT_FOUND, gameId, language));
        return gameNameMapper.toResponse(name);
    }

    // ======================
    // Command methods
    // ======================

    // Public API: add GameName (return entity)
    @Override
    @Transactional
    public GameName addGameName(String gameId, GameNameRequest request) {
        return doAddGameName(gameId, request);
    }

    // Public API: add GameName (return DTO)
    @Override
    @Transactional
    public GameNameResponse addGameNameAndResponseDto(String gameId, GameNameRequest request) {
        GameName entity = doAddGameName(gameId, request);
        return gameNameMapper.toResponse(entity);
    }

    // Private helper method (shared logic)
    private GameName doAddGameName(String gameId, GameNameRequest request) {
        languageValidator.validateExistsByCode(request.getLanguage());

        if (gameNameRepository.existsByGame_IdAndLanguage_Code(gameId, request.getLanguage())) {
            throw new GameNameException(StatusCode.GAME_NAME_DUPLICATE_LANGUAGE, request.getLanguage());
        }

        if (request.getDefaultName() &&
                gameNameRepository.findByGame_IdAndDefaultNameTrue(gameId).isPresent()) {
            throw new GameNameException(StatusCode.GAME_NAME_DEFAULT_EXISTS);
        }

        GameName gameName = gameNameMapper.toEntity(request);
        // attach gameId
        Game game = new Game();
        game.setId(gameId);
        gameName.setGame(game);

        return gameNameRepository.save(gameName);
    }

    // Update existing GameName
    @Override
    @Transactional
    public GameNameResponse updateGameName(Long id, GameNameRequest request) {
        languageValidator.validateExistsByCode(request.getLanguage());

        GameName gameName = gameNameRepository.findById(id)
                .orElseThrow(() -> new GameNameException(StatusCode.GAME_NAME_NOT_FOUND, id));

        // Check duplicate language if language is changed
        if (!gameName.getLanguage().getCode().equals(request.getLanguage()) &&
                gameNameRepository.existsByGame_IdAndLanguage_Code(gameName.getGame().getId(), request.getLanguage())) {
            throw new GameNameException(StatusCode.GAME_NAME_DUPLICATE_LANGUAGE, request.getLanguage());
        }

        // Handle default name logic
        if (request.getDefaultName()) {
            if (!gameName.isDefaultName()) {
                gameNameRepository.findByGame_IdAndDefaultNameTrue(gameName.getGame().getId())
                        .ifPresent(existingDefault -> {
                            existingDefault.setDefaultName(false);
                            gameNameRepository.save(existingDefault);
                        });
            }
            gameName.setDefaultName(true);
        } else {
            if (gameName.isDefaultName()) {
                boolean hasOtherDefault = gameNameRepository.findByGame_IdAndDefaultNameTrue(gameName.getGame().getId())
                        .filter(existingDefault -> !existingDefault.getId().equals(gameName.getId()))
                        .isPresent();

                if (!hasOtherDefault) {
                    throw new GameNameException(StatusCode.GAME_NAME_DEFAULT_REQUIRED);
                }
            }
            gameName.setDefaultName(false);
        }

        gameNameMapper.updateEntityFromRequest(request, gameName);

        return gameNameMapper.toResponse(gameNameRepository.save(gameName));
    }

    // Delete GameName by id
    @Override
    @Transactional
    public void deleteGameName(Long id) {
        GameName gameName = gameNameRepository.findById(id)
                .orElseThrow(() -> new GameNameException(StatusCode.GAME_NAME_NOT_FOUND, id));

        long count = gameNameRepository.countByGame_Id(gameName.getGame().getId());
        if (count <= 1) {
            throw new GameNameException(StatusCode.GAME_NAME_MIN_REQUIRED);
        }

        if (gameName.isDefaultName()) {
            boolean hasOtherDefault = gameNameRepository.findByGame_IdAndDefaultNameTrue(gameName.getGame().getId())
                    .filter(existingDefault -> !existingDefault.getId().equals(gameName.getId()))
                    .isPresent();

            if (!hasOtherDefault) {
                throw new GameNameException(StatusCode.GAME_NAME_DEFAULT_REQUIRED);
            }
        }

        gameNameRepository.deleteById(id);
    }
}
