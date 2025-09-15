package com.smilegate.game_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    @Id
    @Column(length = 50, nullable = false)
    String id;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    Category category;

    @Column(length = 255)
    String image;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    List<GameName> names;

}
