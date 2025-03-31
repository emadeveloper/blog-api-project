package com.blog_spring_boot_api.blog_spring_boot_api.services;

import java.util.List;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;

public interface CommentService {
    public CommentDTO createComment(Long postId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPostId(Long postId);

    public CommentDTO getCommentById(Long id, Long postId);

    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest);

    public void deleteComment (Long postId, Long commentId);
}
