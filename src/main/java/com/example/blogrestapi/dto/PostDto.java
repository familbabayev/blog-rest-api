package com.example.blogrestapi.dto;

import com.example.blogrestapi.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String description;
    private Set<Comment> comments;
}
