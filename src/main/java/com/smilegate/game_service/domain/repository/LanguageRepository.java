package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, String> {
}
