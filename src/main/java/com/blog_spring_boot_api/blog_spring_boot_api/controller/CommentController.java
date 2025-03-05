package com.blog_spring_boot_api.blog_spring_boot_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.services.CommentService;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsById(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        if (commentDTO != null) {
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "postId") long postId,
            @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

}
