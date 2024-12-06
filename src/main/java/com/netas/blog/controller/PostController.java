package com.netas.blog.controller;

import com.netas.blog.dto.PostDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/save")
    public ResponsePayload<PostDto> save(@RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

    @GetMapping(value = "/listOfPosts")
    public ResponsePayload<List<PostDto>> getList() {
        return postService.list();
    }
    @GetMapping(value="/getPostById")
    public ResponsePayload<PostDto> getById(@RequestParam Long id ){
        return postService.getById(id);
    }
    @GetMapping(value = "/listOfPostsByUserId")
    public ResponsePayload<List<PostDto>> getPostsByUserId(@RequestParam Long userId) {
        return postService.getPostsByUserId(userId);
    }
    @PostMapping("/by-categories")
    public ResponsePayload<List<PostDto>> getPostsByCategories(@RequestBody List<Long> categoryIds) {
        return postService.getPostsByCategoryIds(categoryIds);
    }

    @DeleteMapping(value = "/delete")
    public ResponsePayload<Void> deletePost(@RequestParam Long id) {
        return postService.delete(id);
    }

    @PutMapping(value = "/update")
    public ResponsePayload<PostDto> updatePost(@RequestBody PostDto postDto) {
        return postService.update(postDto);
    }

}
