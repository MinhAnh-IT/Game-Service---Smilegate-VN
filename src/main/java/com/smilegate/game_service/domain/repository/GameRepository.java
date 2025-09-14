package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
}
