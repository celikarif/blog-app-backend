package com.netas.blog.mapper;

import com.netas.blog.dto.PostDto;
import com.netas.blog.entity.Category;
import com.netas.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.netas.blog.service.Impl.ImageService.saveBase64Image;

@Component
public class PostDtoMapper {
    public PostDto convertToDto(Post post) {
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public Post convertToEntity(PostDto postDto)  {
        Post post = new Post();

        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setUpdatedAt(postDto.getUpdatedAt());
        return post;
    }

    public List<PostDto> mapList(List<Post> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Post> convertListToEntity(List<PostDto> list) {
       return list.stream().map(this::convertToEntity).toList();
    }

    public PostDto mapWithObjects(Post post) {
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .userDto(new UserDtoMapper().convertToDto(post.getAuthor()))
                .commentDtoList(new CommentDtoMapper().mapList(post.getComments()))
                .categoryDtoList(new CategoryDtoMapper().mapList(post.getCategories()))
                .build();
    }

    public List<PostDto> mapListWithObjects(List<Post> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }


    public Post convertToEntityWithObject(PostDto postDto)  {
        Post post = new Post();
        if (postDto.getImageUrl() != null && postDto.getImageUrl().startsWith("data:image")) {
            String imageUrl = saveBase64Image(postDto.getImageUrl());
            post.setImageUrl(imageUrl);
        }
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setUpdatedAt(postDto.getUpdatedAt());
        post.setCategories(new CategoryDtoMapper().convertListToEntity(postDto.getCategoryDtoList()));
        post.setAuthor(new UserDtoMapper().convertToEntity(postDto.getUserDto()));
        return post;
    }

}

