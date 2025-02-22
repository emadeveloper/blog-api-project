package com.blog_spring_boot_api.blog_spring_boot_api.services;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;

public interface PostService {
    PostDTO createPost (PostDTO postDTO);
}
