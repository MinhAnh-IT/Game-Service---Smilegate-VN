package com.smilegate.game_service.infrastructure.storage.impl;

import com.smilegate.game_service.exception.FileStorageException;
import com.smilegate.game_service.infrastructure.storage.StorageService;
import com.smilegate.game_service.infrastructure.storage.config.StorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalFileStorageService implements StorageService {

    private final StorageProperties storageProperties;

    @Override
    public String store(MultipartFile file) {
        try {
            String uploadDir = storageProperties.getUploadDir();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            return "/" + uploadDir + filename;
        } catch (IOException e) {
            throw new FileStorageException(file.getOriginalFilename(), e);
        }
    }

    @Override
    public byte[] readFile(String filename) {
        try {
            Path filePath = Paths.get(storageProperties.getUploadDir()).resolve(filename);

            if (!Files.exists(filePath)) {
                throw new FileNotFoundException(filename);
            }

            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FileStorageException(filename, e);
        }
    }

}
