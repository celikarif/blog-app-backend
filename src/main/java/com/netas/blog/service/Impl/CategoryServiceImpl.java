package com.netas.blog.service.Impl;

import com.netas.blog.dto.CategoryDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.entity.Category;
import com.netas.blog.enumaration.core.MessageEnum;
import com.netas.blog.enumaration.core.ResponseEnum;
import com.netas.blog.mapper.CategoryDtoMapper;
import com.netas.blog.repository.CategoryRepository;
import com.netas.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper mapper;

    @Override
    public ResponsePayload<CategoryDto> save(CategoryDto categoryDto) {
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(),
                mapper.convertToDto(categoryRepository.save(mapper.convertToEntity(categoryDto))));
    }
    @Override
    public ResponsePayload<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = mapper.mapList(categories);
        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.RETRIEVE_SUCCESS.getMessage(),
                categoryDtos
        );
    }

    @Override
    public ResponsePayload<Void> deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            return new ResponsePayload<>(
                    ResponseEnum.NOTFOUND,
                    MessageEnum.NOT_FOUND.getMessage()
            );
        }
        categoryRepository.deleteById(id);
        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.DELETE_SUCCESS.getMessage()
        );
    }

    @Override
    public ResponsePayload<CategoryDto> updateCategory( CategoryDto categoryDto) {
        if (!categoryRepository.existsById(categoryDto.getId())) {
            return new ResponsePayload<>(
                    ResponseEnum.NOTFOUND,
                    MessageEnum.NOT_FOUND.getMessage()
            );
        }
        Category category = mapper.convertToEntity(categoryDto);
        Category updatedCategory = categoryRepository.save(category);
        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.UPDATE_SUCCESS.getMessage(),
                mapper.convertToDto(updatedCategory)
        );
    }
}
