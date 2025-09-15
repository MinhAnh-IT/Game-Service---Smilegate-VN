package com.smilegate.game_service.infrastructure.storage.config;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StorageProperties {

    @Value("${app.storage.upload-dir}")
    String uploadDir;
}
