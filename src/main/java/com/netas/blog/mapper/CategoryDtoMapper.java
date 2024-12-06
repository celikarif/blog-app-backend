package com.netas.blog.mapper;

import com.netas.blog.dto.CategoryDto;
import com.netas.blog.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDtoMapper {
    public CategoryDto convertToDto(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    public List<CategoryDto> mapList(List<Category> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Category> convertListToEntity(List<CategoryDto> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public CategoryDto mapWithObjects(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .postDtoList(new PostDtoMapper().mapListWithObjects(category.getPosts()))
                .build();
    }
    public List<CategoryDto> mapListWithObjects(List<Category> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }
}
