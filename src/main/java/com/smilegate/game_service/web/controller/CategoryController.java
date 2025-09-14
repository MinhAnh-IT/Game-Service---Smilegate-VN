package com.smilegate.game_service.web.controller;

import com.smilegate.game_service.application.service.category.CategoryQueryService;
import com.smilegate.game_service.common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryQueryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCategories(){
        var categories = categoryService.getCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }
}
