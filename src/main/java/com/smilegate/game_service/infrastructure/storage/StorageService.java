package com.smilegate.game_service.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file);
    byte[] readFile(String filename);
}
