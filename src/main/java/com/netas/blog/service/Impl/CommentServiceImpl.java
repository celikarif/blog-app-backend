package com.netas.blog.service.Impl;

import com.netas.blog.dto.CommentDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.entity.Comment;
import com.netas.blog.enumaration.core.MessageEnum;
import com.netas.blog.enumaration.core.ResponseEnum;
import com.netas.blog.mapper.CommentDtoMapper;
import com.netas.blog.repository.CommentRepository;
import com.netas.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoMapper mapper;

    @Override
    public ResponsePayload<CommentDto> save(CommentDto commentDto) {
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(),
                mapper.convertToDto(commentRepository.save(mapper.convertToEntity(commentDto))));
    }

    @Override
    public ResponsePayload<Void> delete(Long id) {
        if (!commentRepository.existsById(id)) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
        }
        commentRepository.deleteById(id);
        return new ResponsePayload<>(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage());
    }

    @Override
    public ResponsePayload<CommentDto> update( CommentDto commentDto) {
        Optional<Comment> existingCommentOpt = commentRepository.findById(commentDto.getId());
        if (existingCommentOpt.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, MessageEnum.NOT_FOUND.getMessage());
        }

        Comment existingComment = existingCommentOpt.get();

        Comment updatedComment = mapper.convertToEntity(commentDto);
        updatedComment.setId(existingComment.getId());

        Comment savedComment = commentRepository.save(updatedComment);

        return new ResponsePayload<>(
                ResponseEnum.OK,
                MessageEnum.UPDATE_SUCCESS.getMessage(),
                mapper.convertToDto(savedComment)
        );
    }


    @Override
    public ResponsePayload<List<CommentDto>> getAllCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            return new ResponsePayload<>(ResponseEnum.NOTFOUND, "No comments found for the given post ID.");
        }
        List<CommentDto> commentDtos = comments.stream()
                .map(mapper::mapWithObjects)
                .toList();
        return new ResponsePayload<>(ResponseEnum.OK, "Comments fetched successfully.", commentDtos);
    }

}
