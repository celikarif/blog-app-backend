package com.netas.blog.service;

import com.netas.blog.dto.PostDto;
import com.netas.blog.dto.core.ResponsePayload;

import java.io.IOException;
import java.util.List;

public interface PostService {
    ResponsePayload<PostDto> save(PostDto postDto) ;
    ResponsePayload<List<PostDto>> list();
    ResponsePayload<PostDto> getById(Long id);
    ResponsePayload<List<PostDto>> getPostsByUserId(Long userId);
    ResponsePayload<List<PostDto>> getPostsByCategoryIds(List<Long> categoryIds);
    ResponsePayload<Void> delete(Long id);
    ResponsePayload<PostDto> update(PostDto postDto) ;

}
