package com.example.blogrestapi.service;

import com.example.blogrestapi.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long postId);
}
