package com.example.blogrestapi.service;

import com.example.blogrestapi.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

}
