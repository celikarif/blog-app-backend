package com.netas.blog.service;

import com.netas.blog.dto.CommentDto;
import com.netas.blog.dto.core.ResponsePayload;

import java.util.List;

public interface CommentService {

    ResponsePayload<CommentDto> save(CommentDto commentDto);
    ResponsePayload<Void> delete(Long id);
    ResponsePayload<CommentDto> update( CommentDto commentDto);
    ResponsePayload<List<CommentDto>> getAllCommentsByPostId(Long postId);

}
