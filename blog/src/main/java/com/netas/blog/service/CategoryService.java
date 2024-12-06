package com.netas.blog.service;

import com.netas.blog.dto.CategoryDto;
import com.netas.blog.dto.core.ResponsePayload;

import java.util.List;

public interface CategoryService {
    ResponsePayload<CategoryDto> save(CategoryDto categoryDto);
    ResponsePayload<List<CategoryDto>> getAllCategories();
    ResponsePayload<Void> deleteCategory(Long id);
    ResponsePayload<CategoryDto> updateCategory( CategoryDto categoryDto);


}
