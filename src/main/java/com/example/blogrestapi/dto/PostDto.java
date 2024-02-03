package com.example.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String description;
}
