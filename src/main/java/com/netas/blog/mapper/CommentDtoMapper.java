package com.netas.blog.mapper;

import com.netas.blog.dto.CommentDto;
import com.netas.blog.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDtoMapper {
    public CommentDto convertToDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public Comment convertToEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setPost(new PostDtoMapper().convertToEntity(commentDto.getPostDto()));
      //  comment.setUser(new UserDtoMapper().convertToEntity(commentDto.getUserDto()));
        return comment;
    }

    public List<CommentDto> mapList(List<Comment> list) {
        return list.stream().map(this::convertToDto).toList();
    }

    public List<Comment> convertListToEntity(List<CommentDto> list) {
        return list.stream().map(this::convertToEntity).toList();
    }

    public CommentDto mapWithObjects(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .postDto(new PostDtoMapper().convertToDto(comment.getPost()))
                .userDto(new UserDtoMapper().convertToDto(comment.getUser()))
                .build();
    }

    public List<CommentDto> mapListWithObjects(List<Comment> list) {
        return list.stream().map(this::mapWithObjects).toList();
    }


}
