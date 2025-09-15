package com.smilegate.game_service.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Language {

    @Id
    @Column(length = 2)
    String code; // EN, KO, JA

    @Column(nullable = false, length = 50)
    String name; // English, Korean, Japanese
}
