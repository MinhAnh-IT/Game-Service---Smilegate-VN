package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.GameName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameNameRepository extends JpaRepository<GameName, Long> {
}
