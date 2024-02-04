package com.example.blogrestapi.controller;

import com.example.blogrestapi.dto.CommentDto;
import com.example.blogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId,
                                                    @RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId, commentDto));
    }

}
