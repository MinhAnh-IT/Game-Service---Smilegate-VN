package com.smilegate.game_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

    @Id
    @Column(length = 32, nullable = false)
    String code;

    @Column(name = "display_name", length = 100, nullable = false)
    String displayName;

    @Column(name = "created_at", updatable = false, insertable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
