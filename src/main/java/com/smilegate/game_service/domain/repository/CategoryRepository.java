package com.smilegate.game_service.domain.repository;

import com.smilegate.game_service.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByDisplayName(String displayName);

    boolean existsByDisplayNameAndCodeNot(String displayName, String code);
}
