package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, String> {
    Page<Game> findByCategory_Code(String category, Pageable pageable);
    Page<Game> findByNames_ValueContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Game> findByCategory_CodeAndNames_ValueContainingIgnoreCase(String category, String keyword, Pageable pageable);
    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.names WHERE g.id = :id")
    Optional<Game> findByIdWithNames(@Param("id") String id);
}
