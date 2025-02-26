package com.blog_spring_boot_api.blog_spring_boot_api.services;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostResponse;

public interface PostService {
    
    public PostDTO createPost (PostDTO postDTO);

    public PostResponse getAllPosts(int pageNumber, int pageSize);

    public PostDTO getPostById(long id);

    public PostDTO updatePost(PostDTO postDTO, long id);
    
    public void deletePost(long id);
}
