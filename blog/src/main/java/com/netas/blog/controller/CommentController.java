package com.netas.blog.controller;


import com.netas.blog.dto.CommentDto;
import com.netas.blog.dto.core.ResponsePayload;
import com.netas.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/save")
    public ResponsePayload<CommentDto> save(@RequestBody CommentDto commentDto) {
        return commentService.save(commentDto);
    }
    @DeleteMapping(value = "/delete")
    public ResponsePayload<Void> delete(@RequestParam("id") Long id) {
        return commentService.delete(id);
    }
    @PutMapping(value = "/update")
    public ResponsePayload<CommentDto> update( @RequestBody CommentDto commentDto) {
        return commentService.update( commentDto);
    }
    @GetMapping(value = "/getAllCommentsByPostId")
    public ResponsePayload<List<CommentDto>> getAllCommentsByPostId(@RequestParam("postId") Long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }


}
