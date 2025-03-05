package com.blog_spring_boot_api.blog_spring_boot_api.services;

import java.util.List;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;

public interface CommentService {
    public CommentDTO createComment (long postId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPostId(long postId);

    public CommentDTO getCommentById(long id, long postId);

}
