package com.smilegate.game_service.web.controller;

import com.smilegate.game_service.application.dto.request.CategoryRequest;
import com.smilegate.game_service.application.dto.response.CategoryResponse;
import com.smilegate.game_service.application.service.category.CategoryCommandService;
import com.smilegate.game_service.application.service.category.CategoryQueryService;
import com.smilegate.game_service.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryQueryService categoryQueryService;
    CategoryCommandService categoryCommandService;

    //Get all categories
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories() {
        var categories = categoryQueryService.getCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request) {
        var category = categoryCommandService.createCategory(request);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    //Update an existing category by code
    @PutMapping("/{code}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable String code,
            @Valid @RequestBody CategoryRequest request) {
        var category = categoryCommandService.updateCategory(code, request);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    //Delete a category by code
    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable String code) {
        categoryCommandService.deleteCategory(code);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
