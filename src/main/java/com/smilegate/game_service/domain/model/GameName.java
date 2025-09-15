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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    Game game;

    @ManyToOne
    @JoinColumn(name = "language", referencedColumnName = "code", nullable = false)
    private Language language;

    @Column(nullable = false, length = 255)
    String value;

    @Column(name = "default_name", nullable = false)
    boolean defaultName;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
