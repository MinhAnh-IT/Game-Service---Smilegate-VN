package com.smilegate.game_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_names")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    Game game;

    @Column(length = 2, nullable = false)
    String language; // EN, KO, JA

    @Column(nullable = false, length = 255)
    String value;

    @Column(name = "is_default", nullable = false)
    boolean isDefault;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
