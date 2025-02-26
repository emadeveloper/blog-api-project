package com.blog_spring_boot_api.blog_spring_boot_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostResponse;
import com.blog_spring_boot_api.blog_spring_boot_api.services.PostService;
import com.blog_spring_boot_api.blog_spring_boot_api.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    // Pagination
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER_BY_DEFAULT, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_BY_DEFAULT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
            @RequestParam(value = "order", defaultValue = AppConstants.ORDER_BY_DEFAULT, required = false) String order) {
        return postService.getAllPosts(pageNumber, pageSize, sortBy, order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable(name = "id") long id, @RequestBody PostDTO postDTO) {
        PostDTO postDTOResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
