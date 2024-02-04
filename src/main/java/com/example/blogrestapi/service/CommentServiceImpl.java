package com.example.blogrestapi.service;

import com.example.blogrestapi.dto.CommentDto;
import com.example.blogrestapi.entity.Comment;
import com.example.blogrestapi.entity.Post;
import com.example.blogrestapi.exception.ResourceNotFoundException;
import com.example.blogrestapi.repository.CommentRepository;
import com.example.blogrestapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }

    private CommentDto mapToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

}
