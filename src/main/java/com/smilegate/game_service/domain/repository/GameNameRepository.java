package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.GameName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameNameRepository extends JpaRepository<GameName, Long> {

    List<GameName> findByGame_Id(String gameId);

    Optional<GameName> findByGame_IdAndLanguage_Code(String gameId, String language);

    Optional<GameName> findByGame_IdAndDefaultNameTrue(String gameId);

    boolean existsByGame_IdAndLanguage_Code(String gameId, String language);

    long countByGame_Id(String id);
}
