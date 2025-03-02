package com.blog_spring_boot_api.blog_spring_boot_api.services;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;

public interface CommentService {
    public CommentDTO createComment (long postId, CommentDTO commentDTO);
    
}
