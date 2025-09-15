package com.smilegate.game_service.application.service.language.impl;

import com.smilegate.game_service.application.dto.response.LanguageResponse;
import com.smilegate.game_service.application.mapper.LanguageMapper;
import com.smilegate.game_service.application.service.language.LanguageQueryService;
import com.smilegate.game_service.application.service.language.LanguageValidator;
import com.smilegate.game_service.common.constant.StatusCode;
import com.smilegate.game_service.domain.model.Language;
import com.smilegate.game_service.domain.repository.LanguageRepository;
import com.smilegate.game_service.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LanguageServiceImpl implements LanguageQueryService, LanguageValidator {

    LanguageRepository languageRepository;
    LanguageMapper languageMapper;

    @Override
    public List<LanguageResponse> getAllLanguages() {
        var languages = languageRepository.findAll();
        return languages.stream()
                .map(languageMapper::toResponse)
                .toList();
    }

    @Override
    public void validateExistsByCode(String code) {
        boolean exists = languageRepository.existsById(code);
        if (!exists) {
            throw new NotFoundException(StatusCode.LANGUAGE_NOT_FOUND, code);
        }
    }

    @Override
    public Language getByCode(String code) {
        return languageRepository.findById(code)
                .orElseThrow(() -> new NotFoundException(StatusCode.LANGUAGE_NOT_FOUND, code));
    }

}
