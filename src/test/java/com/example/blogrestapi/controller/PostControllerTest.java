package com.example.blogrestapi.controller;

import com.example.blogrestapi.dto.PostDto;
import com.example.blogrestapi.dto.PostResponse;
import com.example.blogrestapi.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private PostDto postDto;

    @BeforeEach
    void setUp() {
        postDto = PostDto.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .description("Test Description")
                .build();
    }


    @Test
    void getAllPosts() throws Exception {
        Mockito.when(postService.getAllPosts(0, 10, "id", "asc"))
                .thenReturn(PostResponse.builder()
                        .content(List.of(postDto))
                        .pageNo(0)
                        .pageSize(10)
                        .totalElements(1)
                        .totalPages(1)
                        .last(true)
                        .build());


        mockMvc.perform(get("/api/posts?pageNo=0&pageSize=10&sortBy=id&sortDir=asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].title").value("Test Title"))
                .andExpect(jsonPath("$.content[0].content").value("Test Content"))
                .andExpect(jsonPath("$.content[0].description").value("Test Description"))
                .andExpect(jsonPath("$.pageNo").value(0))
                .andExpect(jsonPath("$.pageSize").value(10))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.last").value(true));
    }

    @Test
    void getPostById() throws Exception {
        Mockito.when(postService.getPostById(1L)).thenReturn(postDto);

        mockMvc.perform(get("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void createPost() throws Exception {
        Mockito.when(postService.createPost(any(PostDto.class))).thenReturn(postDto);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title":"Test Title",
                                    "content":"Test Content",
                                    "description":"Test Description"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void updatePost() throws Exception {
        PostDto updatedPostDto = PostDto.builder()
                .title("Updated Title")
                .content("Updated Content")
                .description("Updated Description")
                .build();

        Mockito.when(postService.updatePost(any(PostDto.class), eq(1L))).thenReturn(updatedPostDto);

        mockMvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title":"Updated Title",
                                    "content":"Updated Content",
                                    "description":"Updated Description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.content").value("Updated Content"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void deletePost() throws Exception {
        mockMvc.perform(delete("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Post deleted successfully!"));
    }


}