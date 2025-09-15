package com.smilegate.game_service.application.service.language;

import com.smilegate.game_service.application.dto.response.LanguageResponse;
import com.smilegate.game_service.domain.model.Language;

import java.util.List;

public interface LanguageQueryService {
    List<LanguageResponse> getAllLanguages();
    Language getByCode(String code);
}
