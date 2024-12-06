package com.netas.blog.service.Impl;

import com.netas.blog.dto.PostDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.entity.Category;
import com.netas.blog.entity.Post;
import com.netas.blog.enumaration.core.MessageEnum;
import com.netas.blog.enumaration.core.ResponseEnum;
import com.netas.blog.mapper.PostDtoMapper;
import com.netas.blog.repository.CategoryRepository;
import com.netas.blog.repository.PostRepository;
import com.netas.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.netas.blog.service.Impl.ImageService.deleteImageFile;
import static com.netas.blog.service.Impl.ImageService.saveBase64Image;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostDtoMapper mapper;

    @Override
    public ResponsePayload<PostDto> save(PostDto postDto)  {

        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(),
                mapper.convertToDto(postRepository.save(mapper.convertToEntityWithObject(postDto))));
    }


    @Override
    public ResponsePayload<List<PostDto>> list() {
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.RETRIEVE_SUCCESS.getMessage(),
                mapper.mapListWithObjects((List<Post>) postRepository.findAll())
        );
    }

    @Override
    public ResponsePayload<PostDto> getById(Long id) {
        return postRepository.findById(id)
                .map(post -> new ResponsePayload<>(
                        ResponseEnum.OK,
                        MessageEnum.RETRIEVE_SUCCESS.getMessage(),
                        mapper.mapWithObjects(post)
                ))
                .orElseGet(() -> new ResponsePayload<>(
                        ResponseEnum.NOTFOUND,
                        MessageEnum.NOT_FOUND.getMessage()
                ));
    }
    @Override
    public ResponsePayload<List<PostDto>> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findAllByAuthorId(userId);

        if (posts.isEmpty()) {
            return new ResponsePayload<>(
                    ResponseEnum.NOTFOUND,
                    MessageEnum.EMPTY_LIST.getMessage()
            );
        }

        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.RETRIEVE_SUCCESS.getMessage(),
                mapper.mapList(posts)
        );
    }


    public ResponsePayload<List<PostDto>> getPostsByCategoryIds(List<Long> categoryIds) {
        List<Post> posts = postRepository.findAllByCategoryIds(categoryIds);

        if (posts.isEmpty()) {
            return new ResponsePayload<>(
                    ResponseEnum.NOTFOUND,
                    MessageEnum.EMPTY_LIST.getMessage()
            );
        }

        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.RETRIEVE_SUCCESS.getMessage(),
                mapper.mapList(posts)
        );
    }

    @Override
    public ResponsePayload<Void> delete(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getImageUrl() != null) {
                deleteImageFile(post.getImageUrl());
            }
            postRepository.deleteById(id);

            return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage());
        } else {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
        }
    }


    @Override
    public ResponsePayload<PostDto> update(PostDto postDto) {
        if (postDto.getId() == null || !postRepository.existsById(postDto.getId())) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
        }

        Post existingPost = postRepository.findById(postDto.getId()).orElseThrow();

        if (postDto.getImageUrl() != null) {
            existingPost.setImageUrl(postDto.getImageUrl());
        }

        Post updatedPost = postRepository.save(existingPost);

        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.UPDATE_SUCCESS.getMessage(),
                mapper.convertToDto(updatedPost));
    }

}

