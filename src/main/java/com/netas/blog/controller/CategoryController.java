package com.netas.blog.controller;


import com.netas.blog.dto.CategoryDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "/save")
    public ResponsePayload<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }
    @GetMapping
    public ResponsePayload<List<CategoryDto>> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @DeleteMapping("/delete")
    public ResponsePayload<Void> deleteCategory(@RequestParam Long id) {
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/update")
    public ResponsePayload<CategoryDto> updateCategory( @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory( categoryDto);
    }
}
